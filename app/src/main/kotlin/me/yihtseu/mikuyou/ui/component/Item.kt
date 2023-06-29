package me.yihtseu.mikuyou.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.yihtseu.mikuyou.api.VideoInfo

@Composable
fun Item(video: VideoInfo, onClick: (String) -> Unit ) {
    Card(
        modifier = Modifier
            .padding(10.dp, 3.dp)
            .fillMaxWidth()
            .clickable { onClick(video.bvid) }
        ,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row (
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                modifier = Modifier.weight(0.4f),
                model = video.pic.replace("http", "https"),
                contentDescription = null
            )
            Column (
                modifier = Modifier
                    .weight(0.6f)
                    .padding(5.dp)
            ){
                Text(text = video.title)
                Text(text = video.owner.name)
            }
        }
    }
}