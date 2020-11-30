package com.bignerdranch.android.photogallery

import com.google.gson.annotations.SerializedName

//Creating a model object class
data class GalleryItem(
    var title: String = "",
    var id: String = "",
    //24.23 Overriding default name-property mapping
    @SerializedName("url_s") var url: String = ""
)