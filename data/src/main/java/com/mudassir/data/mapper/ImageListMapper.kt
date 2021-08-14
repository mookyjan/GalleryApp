package com.mudassir.data.mapper

import com.mudassir.data.remote.model.ImageListResponse
import com.mudassir.data.util.BaseMapper
import com.mudassir.domain.entity.ImageItemEntity

object ImageListMapper : BaseMapper<ImageItemEntity, ImageListResponse>() {

    override fun transformFrom(source: ImageListResponse): ImageItemEntity {

        return ImageItemEntity(
            source.id,
            source.author,
            source.width,
            source.height,
            source.url,
            source.downloadUrl
        )
    }

    override fun transformTo(source: ImageItemEntity): ImageListResponse = ImageListResponse(
        source.id,
        source.author,
        source.width,
        source.height,
        source.url,
        source.downloadUrl
    )

}