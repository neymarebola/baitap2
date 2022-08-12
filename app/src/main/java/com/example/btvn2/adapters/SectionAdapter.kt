package com.example.btvn2.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.btvn2.R
import com.example.btvn2.activity.WebviewActivity
import com.example.btvn2.models.PreviewImage
import com.example.btvn2.models.Section

class SectionAdapter : RecyclerView.Adapter<SectionAdapter.ViewHolder> {
    val IMG_SECTION = 1
    val VIDEO_SECTION = 2

    var context: Context? = null
    var listSections = mutableListOf<Section>()

    constructor(ct: Context, list: List<Section>) {
        context = ct
        listSections = list as MutableList<Section>
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var previewImage: ImageView? = null
        var mainImage: ImageView? = null
        var caption: TextView? = null
        var text: TextView? = null
        var videoView: VideoView? = null

        init {
            previewImage = itemView.findViewById(R.id.preview_image)
            caption = itemView.findViewById(R.id.tv_caption)
            videoView = itemView.findViewById(R.id.video_view)
            text = itemView.findViewById(R.id.tv_content)
            mainImage = itemView.findViewById(R.id.main_image)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == IMG_SECTION) {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.section_text_item_layout, parent, false)
            ViewHolder(view)
        } else {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.section_video_item_layout, parent, false)
            ViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val section = listSections[position]
        // bai viet chua video
        // bai viet chua video
        if (section.sectionType === 2) {
            // preview image
            val previewImage: PreviewImage = section.content?.previewImage!!
            if (previewImage != null) {
                if (previewImage.href != null) {
                    Glide.with(context!!).load(previewImage.href)
                        .centerCrop()
                        .placeholder(R.drawable.img_placeholder)
                        .into(holder.previewImage!!)
                }
            }
            // caption
            if (section.content?.caption != null) {
                holder.caption?.text = section.content!!.caption
            }

            // href
            val href: String = section.content!!.href
            if (href != null) {
                playVideo(href, holder.videoView!!)
            }

            // text
            val text: String = section.content!!.text
            var ss: SpannableString? = SpannableString("")
            if (text != null) {
                if (section.content!!.markUps != null) {
                    for (markup in section.content!!.markUps!!) {
                        val start: Int = markup.start
                        val end: Int = markup.end
                        ss = highLightText(text, start, end)
                        val clickableSpan: ClickableSpan = object : ClickableSpan() {
                            override fun onClick(view: View) {
                                val href: String = markup.href
                                if (href != null && !href.isEmpty()) {
                                    val intent = Intent(context, WebviewActivity::class.java)
                                    intent.putExtra("href", href)
                                    context!!.startActivity(intent)
                                } else {
                                    Toast.makeText(
                                        context,
                                        "link is not available",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                            }

                            override fun updateDrawState(ds: TextPaint) {
                                super.updateDrawState(ds)
                                ds.isUnderlineText = false
                            }
                        }
                        ss!!.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                    }
                }
                holder.text!!.text = ss
                holder.text!!.movementMethod = LinkMovementMethod.getInstance()
            }
        } else {
            if (section.content?.previewImage != null) {
                val previewImage: PreviewImage = section.content?.previewImage!!
                if (previewImage != null) {
                    if (previewImage.href != null) {
                        Glide.with(context!!).load(previewImage.href)
                            .centerCrop()
                            .placeholder(R.drawable.img_placeholder)
                            .into(holder.previewImage!!)
                    }
                }
            }
            if (section.content!!.caption != null) {
                holder.caption?.text = section.content!!.caption
            }

            // caption
            val href: String = section.content!!.href
            if (href != null) {
                Glide.with(context!!).load(href).into(holder.mainImage!!)
            }
            val text: String = section.content!!.text
            var ss: SpannableString? = SpannableString("")
            if (text != null) {
                if (section.content!!.markUps != null) {
                    for (markup in section.content!!.markUps!!) {
                        val start: Int = markup.start
                        val end: Int = markup.end
                        ss = highLightText(text, start, end)
                        val clickableSpan: ClickableSpan = object : ClickableSpan() {
                            override fun onClick(view: View) {
                                val href: String = markup.href
                                val intent = Intent(context, WebviewActivity::class.java)
                                intent.putExtra("href", href)
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                context!!.startActivity(intent)
                            }

                            override fun updateDrawState(ds: TextPaint) {
                                super.updateDrawState(ds)
                                ds.isUnderlineText = false
                            }
                        }
                        ss!!.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                    }
                }
                holder.text!!.text = ss
                holder.text!!.movementMethod = LinkMovementMethod.getInstance()
            }
        }
    }

    override fun getItemCount(): Int {
        return listSections.size
    }

    fun highLightText(text: String?, s: Int, e: Int): SpannableString? {
        val ss = SpannableString(text)
        val boldSpan = StyleSpan(Typeface.BOLD)
        ss.setSpan(boldSpan, s, e, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return ss
    }

    fun playVideo(href: String?, videoView: VideoView) {
        val uri = Uri.parse(href)

        // sets the resource from the
        // videoUrl to the videoView
        videoView.setVideoURI(uri)
        videoView.seekTo(1)
        val mediaController = MediaController(context)

        // sets the anchor view
        // anchor view for the videoView
        mediaController.setAnchorView(videoView)

        // sets the media player to the videoView
        mediaController.setMediaPlayer(videoView)

        // sets the media controller to the videoView
        videoView.setMediaController(mediaController)
    }

    override fun getItemViewType(position: Int): Int {
        val section: Section = listSections.get(position)
        return if (section.sectionType === 2) {
            VIDEO_SECTION
        } else {
            IMG_SECTION
        }
    }
}