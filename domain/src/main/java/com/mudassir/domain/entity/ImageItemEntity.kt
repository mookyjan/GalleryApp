package com.mudassir.domain.entity

data class ImageItemEntity(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val downloadUrl: String
)