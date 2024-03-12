package com.poqndj.testcompose.ui.theme

import com.poqndj.testcompose.domain.FeedPost
import com.poqndj.testcompose.domain.PostComment

sealed class CommentsScreenState {

    object Initial: CommentsScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
    ): CommentsScreenState()
}