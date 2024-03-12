package com.poqndj.testcompose.ui.theme

import android.content.DialogInterface.OnClickListener
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.poqndj.testcompose.CommentsViewModel
import com.poqndj.testcompose.NewsFeedViewModel
import com.poqndj.testcompose.domain.FeedPost

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    onClickListener: (FeedPost) -> Unit
) {
    val viewModel : NewsFeedViewModel = viewModel()
    val screenState = viewModel.screenState.observeAsState(NewsFeedScreenState.Initial)

    when (val currentState = screenState.value) {
        is NewsFeedScreenState.Posts -> {
            FeedPosts(
                posts = currentState.post,
                viewModel = viewModel,
                paddingValues = paddingValues,
                onClickListener = onClickListener
            )
        }
        else -> {}
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FeedPosts(
    posts: List<FeedPost>,
    viewModel: NewsFeedViewModel,
    paddingValues: PaddingValues,
    onClickListener: (FeedPost) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues),
        contentPadding = PaddingValues(
            top = 16.dp,
            start = 8.dp,
            end = 8.dp,
            bottom = 72.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = posts,
            key = { it.id }
        ) {feedPosts ->
            val dismissState = rememberDismissState()
            if (dismissState.isDismissed(DismissDirection.EndToStart)){
                viewModel.removePost(feedPosts)
            }
            SwipeToDismiss(
                modifier = Modifier.animateItemPlacement(),
                state = dismissState,
                background = {},
                directions = setOf(DismissDirection.EndToStart),
                dismissContent = {
                    PostCard(
                        feedPost = feedPosts,
                        onViewsClickListener = {statisticItem ->
                            viewModel.updateCount(feedPosts, statisticItem)
                        },
                        onShareClickListener = {statisticItem ->
                            viewModel.updateCount(feedPosts, statisticItem)
                        },
                        onCommentClickListener = {
                            onClickListener(feedPosts)
                        },
                        onLikeClickListener = {statisticItem ->
                            viewModel.updateCount(feedPosts, statisticItem)
                        },
                    )
                }
            )
        }
    }
}