package com.poqndj.testcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.gson.Gson
import com.poqndj.testcompose.domain.FeedPost
import com.poqndj.testcompose.navigation.Screen.Companion.KEY_FEED_POST

fun NavGraphBuilder.homeScreenNavGraph(
    newsFeedScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (FeedPost) -> Unit,
    ) {
    navigation(
        startDestination = Screen.NewsFeed.route,
        route = Screen.Home.route
    ){
        composable(Screen.NewsFeed.route) {
            newsFeedScreenContent()
        }
        composable(
           route = Screen.Comments.route,
            arguments = listOf(
                navArgument(Screen.KEY_FEED_POST) {
                    type = NavType.StringType
                },
            )
        ) {
           val feedPostGson = it.arguments?.getString(KEY_FEED_POST) ?: ""
            val feedPost = Gson().fromJson(feedPostGson, FeedPost::class.java)
            commentsScreenContent(feedPost)
        }
    }
}