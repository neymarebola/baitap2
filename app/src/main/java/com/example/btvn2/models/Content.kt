package com.example.btvn2.models

import com.google.gson.annotations.SerializedName

class Content {
    var href: String = ""
    @SerializedName("preview_image")
    var previewImage: PreviewImage? = null
    var duration: Int = 0

}