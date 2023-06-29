package me.yihtseu.mikuyou.ui.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.yihtseu.mikuyou.api.VideoInfo

@Composable
fun Entry(video: VideoInfo, onClick: (String) -> Unit ) {
    Card(
        modifier = Modifier
            .padding(10.dp, 3.dp)
            .fillMaxWidth()
            .clickable { onClick(video.bvid) }
        ,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column (
            modifier = Modifier.padding(10.dp).fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier.padding(10.dp, 0.dp).fillMaxWidth(),
                model = video.pic.replace("http", "https"),
                contentDescription = null
            )
            Text(text = video.title)
            Text(text = video.owner.name)
        }
    }
}