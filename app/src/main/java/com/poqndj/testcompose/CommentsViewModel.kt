package com.poqndj.testcompose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.poqndj.testcompose.domain.FeedPost
import com.poqndj.testcompose.domain.PostComment
import com.poqndj.testcompose.ui.theme.CommentsScreenState
import com.poqndj.testcompose.ui.theme.NewsFeedScreenState

class CommentsViewModel(
    feedPost: FeedPost
): ViewModel() {

    private val _screenState = MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState : LiveData<CommentsScreenState> = _screenState

    init {
        loadComments(feedPost)
    }

    fun loadComments(feedPost: FeedPost) {
         val comments = mutableListOf<PostComment>().apply {
            repeat(10) {
                add(PostComment(id = it))
            }
        }
        _screenState.value = CommentsScreenState.Comments(
            feedPost = feedPost,
            comments = comments
        )
    }
}