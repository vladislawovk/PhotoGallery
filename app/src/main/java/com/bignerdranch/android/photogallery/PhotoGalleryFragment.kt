package com.bignerdranch.android.photogallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "PhotoGalleryFragment"

class PhotoGalleryFragment : Fragment() {

    //Stash a reference to the ViewModel in a property.
    private lateinit var photoGalleryViewModel: PhotoGalleryViewModel
    private lateinit var photoRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //24.17 Using FlickrFetchr in PhotoGalleryFragment
        //24.20 Executing the "fetch recent interesting photos" request
        //24.28 Updating type specifiers
        //24.31 Getting a ViewModel instance from the provider
        photoGalleryViewModel =
            ViewModelProvider(this).get(PhotoGalleryViewModel::class.java)
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

    //24.32  Observing the ViewModel’s live data
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoGalleryViewModel.galleryItemLiveData.observe(
            viewLifecycleOwner,
            Observer { galleryItems ->
                Log.d(TAG, "Have gallery items from ViewModel $galleryItems")
                // Eventually, update data backing the recycler view
            }
        )
    }

    companion object {
        fun newInstance() = PhotoGalleryFragment()
    }
}