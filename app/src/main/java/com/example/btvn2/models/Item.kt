package com.example.btvn2.models

import com.google.gson.annotations.SerializedName
import java.util.*

class Item {
    @SerializedName("document_id")
    var documentId: String = ""
    var title: String = ""
    var description: String = ""
    @SerializedName("content_type")
    var contentType: String = ""
    @SerializedName("published_date")
    var publishedDate: Date? = null
    var publisher: Publisher? = null
    @SerializedName("origin_url")
    var originUrl: String = ""
    var avatar: Avatar? = null
    var images: List<Image>? = null
    var content: Content? = null
}
