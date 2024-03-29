package com.poqndj.testcompose.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.poqndj.testcompose.R
import com.poqndj.testcompose.domain.FeedPost
import com.poqndj.testcompose.domain.StatisticItem
import com.poqndj.testcompose.domain.StatisticType
import java.lang.IllegalStateException

//@Preview
@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    feedPost: FeedPost,
    onViewsClickListener: (StatisticItem) -> Unit,
    onShareClickListener: (StatisticItem) -> Unit,
    onCommentClickListener: (StatisticItem) -> Unit,
    onLikeClickListener: (StatisticItem) -> Unit
) {
    Card() {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            HeaderCard(feedPost)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = feedPost.contentText,
                color = MaterialTheme.colors.onPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(id = feedPost.contentImageResId),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(8.dp))
            Statistics(
                statistics = feedPost.statistics,
                onLikeClickListener = onLikeClickListener,
                onCommentClickListener = onCommentClickListener,
                onShareClickListener = onShareClickListener,
                onViewsClickListener = onViewsClickListener
            )
        }
    }
}

@Composable
private fun HeaderCard(
    feedPost: FeedPost
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            painter = painterResource(id = feedPost.avatarResId),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(5.dp))
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = feedPost.communityName,
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                text = feedPost.publicationDate,
                color = MaterialTheme.colors.onSecondary
            )
        }
        Spacer(modifier = Modifier.width(5.dp))
        Image(
            imageVector = Icons.Rounded.Email,
            contentDescription = null
        )
    }
}

@Composable
private fun Statistics(
    statistics: List<StatisticItem>,
    onViewsClickListener: (StatisticItem) -> Unit,
    onShareClickListener: (StatisticItem) -> Unit,
    onCommentClickListener: (StatisticItem) -> Unit,
    onLikeClickListener: (StatisticItem) -> Unit
) {
    Row {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            val viewsItem = statistics.getItemByType(StatisticType.VIEWS)
            ImageWithText(
                iconResId = R.drawable.eye,
                text = viewsItem.count.toString(),
                onItemClickListener = {
                    onViewsClickListener(viewsItem)
                }
            )
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val shareItem = statistics.getItemByType(StatisticType.SHARES)
            ImageWithText(
                iconResId = R.drawable.share,
                text = shareItem.count.toString(),
                onItemClickListener = {
                    onShareClickListener(shareItem)
                }
            )

            val commentItem = statistics.getItemByType(StatisticType.COMMENTS)
            ImageWithText(
                iconResId = R.drawable.comment,
                text = commentItem.count.toString(),
                onItemClickListener = {
                    onCommentClickListener(commentItem)
                }
            )
            val likesItem = statistics.getItemByType(StatisticType.LIKES)
            ImageWithText(
                iconResId = R.drawable.like,
                text = likesItem.count.toString(),
                onItemClickListener = {
                    onLikeClickListener(likesItem)
                }
            )
        }
    }
}

private fun List<StatisticItem>.getItemByType(type: StatisticType): StatisticItem {
    return this.find { it.type == type } ?: throw IllegalStateException()
}

@Composable
private fun ImageWithText(
    iconResId: Int,
    text: String,
    onItemClickListener: () -> Unit
) {
    Row(
        modifier = Modifier.clickable {
            onItemClickListener()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = iconResId),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            fontSize = 16.sp,
            text = text
        )
    }
}