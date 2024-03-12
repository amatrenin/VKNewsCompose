package com.poqndj.testcompose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.poqndj.testcompose.domain.FeedPost
import com.poqndj.testcompose.domain.StatisticItem
import com.poqndj.testcompose.ui.theme.NewsFeedScreenState

class NewsFeedViewModel : ViewModel() {

    private val sourceList = mutableListOf<FeedPost>().apply {
       repeat(10) {
           add(FeedPost(
               id = it,
               contentText = "Content text $it"
           )
           )
       }
   }
    private val initialState = NewsFeedScreenState.Posts(post = sourceList)
    private val _screenState = MutableLiveData<NewsFeedScreenState>(initialState)
    val screenState: LiveData<NewsFeedScreenState> = _screenState

    fun updateCount(feedPost: FeedPost, item: StatisticItem) {
        val currentState = screenState.value
        if (currentState !is NewsFeedScreenState.Posts) return

        val oldPost = currentState.post.toMutableList()
        val oldStatistics = feedPost.statistics
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == item.type) {
                    oldItem.copy(count = oldItem.count + 1)
                } else {
                    oldItem
                }
            }
        }
        val newFeedPost = feedPost.copy(statistics = newStatistics)
        val newPost = oldPost.apply {
            replaceAll {
                if (it.id == newFeedPost.id) {
                    newFeedPost
                } else {
                    it
                }
            }
        }
        _screenState.value = NewsFeedScreenState.Posts(post = newPost)
    }

    fun removePost(feedPost: FeedPost) {
        val currentState = screenState.value
        if (currentState !is NewsFeedScreenState.Posts) return

        val oldPost = currentState.post.toMutableList()
        oldPost.remove(feedPost)
        _screenState.value = NewsFeedScreenState.Posts(post = oldPost)
    }
}




