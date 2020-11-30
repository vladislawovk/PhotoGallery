package com.bignerdranch.android.photogallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "PhotoGalleryFragment"

class PhotoGalleryFragment : Fragment() {

    private lateinit var photoRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //24.17 Using FlickrFetchr in PhotoGalleryFragment
        //24.20 Executing the "fetch recent interesting photos" request
        //24.28 Updating type specifiers
        val flickrLiveData: LiveData<List<GalleryItem>> = FlickrFetchr().fetchPhotos()
        flickrLiveData.observe(
            this,
            Observer { galleryItems ->
                Log.d(TAG, "Response received: $galleryItems")
            }
        )
    }

    //24.5 Setting up the fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //24.5 Inflating the layout
        val view = inflater.inflate(R.layout.fragment_photo_gallery, container, false)

        photoRecyclerView = view.findViewById(R.id.photo_recycler_view)
        photoRecyclerView.layoutManager = GridLayoutManager(context, 3)

        return view
    }

    companion object {
        fun newInstance() = PhotoGalleryFragment()
    }
}