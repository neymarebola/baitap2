package com.example.btvn2.models

import com.google.gson.annotations.SerializedName

class ContentDetail {
    var href: String = ""
    var caption: String = ""
    var duration: Int = 0
    @SerializedName("preview_image")
    var previewImage: PreviewImage? = null
    var text: String = ""
    @SerializedName("markups")
    var markUps: List<MarkUp>? = null
    @SerializedName("main_color")
    var mainColor: String = ""
    @SerializedName("original_width")
    var originalWidth: Int = 0
    @SerializedName("original_height")
    var originalHeight: Int = 0
}