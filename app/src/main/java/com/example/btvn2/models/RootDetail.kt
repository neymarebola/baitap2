package com.example.btvn2.models

import com.google.gson.annotations.SerializedName


class RootDetail {
    @SerializedName("document_id")
    var documentId: String = ""
    var title: String = ""
    var description: String = ""
    @SerializedName("published_date")
    var publishedDate: String = ""
    @SerializedName("origin_url")
    var originUrl: String = ""
    var publisher: Publisher? = null
    @SerializedName("template_type")
    var templateType: String = ""
    var sections: List<Section>? = null
}