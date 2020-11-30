package com.bignerdranch.android.photogallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bignerdranch.android.photogallery.api.FlickrApi
import com.bignerdranch.android.photogallery.api.FlickrResponse
import com.bignerdranch.android.photogallery.api.PhotoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "FlickrFetchr"

class FlickrFetchr {

    private val flickrApi: FlickrApi

    //24.8-24.14 Using the Retrofit object to create an instance of the API
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            //Остальная часть URL находится в @GET запросе FlickrApi.kt
            .baseUrl("https://api.flickr.com/")
            //24.10 Adding the converter to the Retrofit object
            //24.27 Updating FlickrFetchr for Gson
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        flickrApi = retrofit.create(FlickrApi::class.java)
    }

    //24.16 Adding fetchContents() to FlickrFetchr
    //24.27 Update fetchPhotos() to return LiveData that wraps a list of gallery items
    fun fetchPhotos(): LiveData<List<GalleryItem>> {
        val responseLiveData: MutableLiveData<List<GalleryItem>> = MutableLiveData()
        val flickrRequest: Call<FlickrResponse> = flickrApi.fetchPhotos()

        flickrRequest.enqueue(object : Callback<FlickrResponse> {

            override fun onFailure(call: Call<FlickrResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch photos", t)
            }

            //24.27 update onResponse() to dig the gallery item list out of the response
            // and update the live data object with the list.
            override fun onResponse(
                call: Call<FlickrResponse>,
                response: Response<FlickrResponse>
            ) {
                Log.d(TAG, "Response received")
                val flickrResponse: FlickrResponse? = response.body()
                val photoResponse: PhotoResponse? = flickrResponse?.photos
                var galleryItems: List<GalleryItem> = photoResponse?.galleryItems
                    ?: mutableListOf()
                //filters out gallery items with blank URL values using filterNot{}.
                galleryItems = galleryItems.filterNot {
                    it.url.isBlank()
                }
                responseLiveData.value = galleryItems
            }
        })

        return responseLiveData
    }
}