package com.poqndj.testcompose.domain

import com.poqndj.testcompose.R

data class PostComment(
    val id: Int,
    val authorName: String = "Author",
    val authorAvatarId: Int = R.drawable.baseline_person_24,
    val commentText: String = "Long comment text",
    val publicationDate: String = "14:00"
)
