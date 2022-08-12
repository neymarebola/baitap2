package com.example.btvn2.models

import com.google.gson.annotations.SerializedName

class MarkUp {
    @SerializedName("markup_type")
    var markUpType: Int = 0
    var start: Int = 0
    var end: Int = 0
    var href: String = ""
}