package com.bignerdranch.android.photogallery.api

import com.bignerdranch.android.photogallery.GalleryItem
import com.google.gson.annotations.SerializedName

//24.24 Adding class for mapping "photos" object in the JSON data
class PhotoResponse {
    @SerializedName("photo")
    lateinit var galleryItems: List<GalleryItem>
}