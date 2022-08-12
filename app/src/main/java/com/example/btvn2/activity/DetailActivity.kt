package com.example.btvn2.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.btvn2.R
import com.example.btvn2.adapters.SectionAdapter
import com.example.btvn2.api_service.JsonPlaceHolderApi
import com.example.btvn2.models.Publisher
import com.example.btvn2.models.RootDetail
import com.example.btvn2.models.Section
import com.example.btvn2.utils.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailActivity : AppCompatActivity() {
    var publisher: TextView? = null
    var date:TextView? = null
    var title:TextView? = null
    var description:TextView? = null
    var publisherIcon: ImageView? = null
    var sectionRec: RecyclerView? = null
    var sectionList = mutableListOf<Section>()
    var sectionAdapter: SectionAdapter? = null

    var detailApi: JsonPlaceHolderApi? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        publisher = findViewById(R.id.tv_publisher)
        date = findViewById(R.id.tv_date)
        title = findViewById(R.id.tv_title)
        publisherIcon = findViewById(R.id.publisher_icon)
        description = findViewById(R.id.tv_description)
        sectionRec = findViewById(R.id.section_rec)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        detailApi = retrofit.create(JsonPlaceHolderApi::class.java)
        getDetail()
    }

    fun getDetail() {
        val call: Call<RootDetail> = detailApi!!.getDetailNewFeed()
        call.enqueue(object : Callback<RootDetail?> {
            override fun onResponse(call: Call<RootDetail?>, response: Response<RootDetail?>) {
                val root_detail: RootDetail? = response.body()
                if (!response.isSuccessful()) {
                    return
                }
                setDataText(root_detail!!)
                // display list section to recyclerView
                sectionList = ArrayList()
                if (root_detail?.sections != null && !root_detail.sections!!.isEmpty()) {
                    sectionList.addAll(root_detail.sections!!)
                }
                sectionAdapter = SectionAdapter(baseContext, sectionList)
                val manager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
                sectionRec!!.layoutManager = manager
                sectionRec!!.adapter = sectionAdapter
            }

            override fun onFailure(call: Call<RootDetail?>, t: Throwable) {}
        })
    }

    fun setDataText(root: RootDetail) {
        val publisher: Publisher = root.publisher!!
        val icon: String = publisher.icon
        if (icon != null) {
            Glide.with(baseContext).load(icon).into(publisherIcon!!) // icon nha xuat ban
        }
        if (publisher.name != null) {
            this.publisher?.text = publisher.name // ten nha xuat ban
        }
        if (root.publishedDate != null) {
            date?.text = root.publishedDate
        }
        if (root.title != null) {
            title?.text  = root.title
        }
        if (root.description != null) {
            description?.text = root.description
        }
    }
}