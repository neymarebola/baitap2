package com.example.btvn2.models

import com.google.gson.annotations.SerializedName

class Section {
    @SerializedName("section_type")
    var sectionType: Int = 0
    var content: ContentDetail? = null
}