package com.example.btvn2.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.btvn2.callback.DiffUtilCallBack
import com.example.btvn2.R
import com.example.btvn2.activity.DetailActivity
import com.example.btvn2.models.Image
import com.example.btvn2.models.Item

class ItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private var context: Context? = null
    private var listItems = mutableListOf<Item>()
    private var imgs = mutableListOf<Image>()

    constructor(ct: Context, list: List<Item>) {
        context = ct
        listItems = list as MutableList<Item>
    }

    val VIDEO_ITEM = 1
    val OTHER_ITEM = 2

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var publisher: TextView
        var published_date: TextView
        var videoView // hien thi video
                : VideoView

        init {
            title = itemView.findViewById(R.id.video_title)
            publisher = itemView.findViewById(R.id.tv_publisher)
            videoView = itemView.findViewById(R.id.video_content)
            published_date = itemView.findViewById(R.id.tv_published_date)
            itemView.setOnClickListener {
                context!!.startActivity(Intent(context, DetailActivity::class.java))
            }
        }
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var publisher: TextView
        var published_date: TextView
        var imgRec // hien thi list cac hinh anh
                : RecyclerView

        init {
            title = itemView.findViewById(R.id.item_title)
            publisher = itemView.findViewById(R.id.tv_publisher)
            imgRec = itemView.findViewById(R.id.images_rec)
            published_date = itemView.findViewById(R.id.tv_published_date)
            itemView.setOnClickListener {
                context!!.startActivity(
                    Intent(
                        context,
                        DetailActivity::class.java
                    )
                )
            }
        }
    }

    public fun addItem(listItem: List<Item>) {
        val diffUtilCallBack = DiffUtilCallBack(listItems, listItem)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallBack)

        listItems.addAll(listItem)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIDEO_ITEM) {
            val view =
                LayoutInflater.from(context).inflate(R.layout.video_item_layout, parent, false)
            VideoViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(context).inflate(R.layout.image_item_layout, parent, false)
            ImageViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = listItems!![position]
        if (item.contentType.equals("video")) {
            holder as VideoViewHolder
            if (holder.title != null) {
                holder.title.setText(item.title)
            }
            if (holder.publisher != null) {
                holder.publisher.setText(item.publisher?.name)
            }
            if (holder.published_date != null) {
                holder.published_date.setText(item.publishedDate.toString())
            }
            if (item.contentType.equals("video")) {
                var uri = Uri.parse(item.content?.href)
                playVideo(uri, holder.videoView)
            }
        } else {
            holder as ImageViewHolder
            if (holder.title != null) {
                holder.title.setText(item.title)
            }
            if (holder.publisher != null) {
                holder.publisher.setText(item.publisher?.name)
            }
            if (holder.published_date != null) {
                holder.published_date.setText(item.publishedDate.toString())
            }
            // hien thi list hinh anh len tren recyclerview
            var tmp: List<Image?>? = ArrayList<Image?>()
            tmp = item.images
            if (tmp != null) {
                imgs.clear()
                imgs?.addAll(tmp)
                val linearLayoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                holder.imgRec.layoutManager = linearLayoutManager
                val itemImagesAdapter = ImageItemAdapter(context, imgs)
                holder.imgRec.adapter = itemImagesAdapter
            }
        }
    }

    fun playVideo(uri: Uri, videoView: VideoView) {

        // sets the resource from the
        // videoUrl to the videoView
        with(videoView) {
            setVideoURI(uri)
            seekTo(1)
        }

        val mediaController = MediaController(context)

        // sets the anchor view
        // anchor view for the videoView

        // sets the anchor view
        // anchor view for the videoView
        mediaController.setAnchorView(videoView)
        // sets the media player to the videoView

        // sets the media player to the videoView
        mediaController.setMediaPlayer(videoView)

        // sets the media controller to the videoView
        // sets the media controller to the videoView
        videoView.setMediaController(mediaController)
    }

    override fun getItemCount(): Int {
        return listItems!!.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (listItems!![position].contentType.equals("video")) {
            VIDEO_ITEM
        } else {
            OTHER_ITEM
        }
    }
}
