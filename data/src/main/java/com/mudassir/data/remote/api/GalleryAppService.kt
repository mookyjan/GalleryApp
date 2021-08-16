package com.mudassir.data.remote.api

import com.mudassir.data.remote.model.ImageListResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GalleryAppService {

    @GET("v2/list")
    fun getImageList(@Query("page") page: Int,
                     @Query("limit") limit: Int=50): Single<List<ImageListResponse>>
}