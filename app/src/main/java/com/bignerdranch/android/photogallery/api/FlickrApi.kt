package com.bignerdranch.android.photogallery.api

import retrofit2.Call
import retrofit2.http.GET

// 24.7 Adding a Retrofit API interface
interface FlickrApi {

    //24.18 Defining the "fetch recent interesting photos" request
    @GET(
        "services/rest/?method=flickr.interestingness.getList" +
                "&api_key=4da65cd06142d72155ac21a0a2eb4ff2" +
                "&format=json" +
                "&nojsoncallback=1" +
                "&extras=url_s"
    )

    //24.26 Updating fetchPhoto()'s return type.
    //indicates to Gson that it should use the FlickrResponse to deserialize the JSON response data.
    fun fetchPhotos(): Call<FlickrResponse>
}