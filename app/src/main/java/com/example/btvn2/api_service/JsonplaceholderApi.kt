package com.example.btvn2.api_service

import com.example.btvn2.models.Root
import com.example.btvn2.models.RootDetail
import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceHolderApi {
    @GET("newsfeed.json")
    fun getListNewFeed(): Call<Root>

    @GET("detail.json")
    fun getDetailNewFeed(): Call<RootDetail>
}