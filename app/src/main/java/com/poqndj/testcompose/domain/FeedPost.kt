package com.poqndj.testcompose.domain

import com.poqndj.testcompose.R

data class FeedPost(
    val id: Int = 0,
    val communityName: String = "/dev/null",
    val publicationDate: String = "14:00",
    val avatarResId: Int = R.drawable.baseline_code_24,
    val contentText: String = "Lorem ipsum dolor sit amlet, Lorem ipsum dolor sit amlet",
    val contentImageResId: Int = R.drawable.back,
    val statistics: List<StatisticItem> = listOf(
        StatisticItem(type = StatisticType.VIEWS, 978),
        StatisticItem(type = StatisticType.SHARES, 7),
        StatisticItem(type = StatisticType.COMMENTS, 8),
        StatisticItem(type = StatisticType.LIKES, 27)
    )
)
