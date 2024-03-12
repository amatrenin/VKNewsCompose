package com.poqndj.testcompose.ui.theme

import com.poqndj.testcompose.domain.FeedPost

sealed class NewsFeedScreenState {

    object Initial: NewsFeedScreenState()

    data class Posts(val post: List<FeedPost>): NewsFeedScreenState()
}