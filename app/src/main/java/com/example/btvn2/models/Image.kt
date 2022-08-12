package com.example.btvn2.models

import com.google.gson.annotations.SerializedName

class Image {
    var href: String = ""
    @SerializedName("main_color")
    var mainColor: String = ""
    var width: Int = 0
    var height: Int = 0
}