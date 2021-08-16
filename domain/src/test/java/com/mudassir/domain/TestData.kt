package com.mudassir.domain

import com.mudassir.domain.entity.ImageItemEntity


//TODO this is dummy data only for checking , will add all the fields to check thoroughly
fun getTestImageEntity(): ImageItemEntity {
    return ImageItemEntity(
        id = "0",
        author = "Alejandro Escamilla",
        width = 5616,
        height = 3744,
        url = "https://unsplash.com/photos/yC-Yzbqy7PY",
        downloadUrl = "https://picsum.photos/id/0/5616/3744"
    )
}

fun generateImageEntityList(): List<ImageItemEntity> {
    return (0..4).map { getTestImageEntity() }
}
