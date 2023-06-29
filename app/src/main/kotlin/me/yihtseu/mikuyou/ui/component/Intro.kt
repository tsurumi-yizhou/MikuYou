package me.yihtseu.mikuyou.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun Intro(name: String, pic: String, desc: String, mid: Long, details: (Long) -> Unit) {
    Card(modifier = Modifier.heightIn(40.dp, 100.dp).padding(10.dp).fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                modifier = Modifier.padding(10.dp).weight(0.3f).clickable { details(mid) },
                model = pic.replace("http://", "https://"),
                contentDescription = null
            )
            Text(
                modifier = Modifier.weight(0.7f), text = name
            )
        }

    }
}