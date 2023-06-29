package me.yihtseu.mikuyou.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.yihtseu.mikuyou.api.TopRegion
import me.yihtseu.mikuyou.api.topRegions

@Composable
fun TagBar(onTagChanged: (Int) -> Unit) {
    var tid by rememberSaveable { mutableStateOf(0) }
    var topRegion by rememberSaveable { mutableStateOf(TopRegion()) }
    LazyRow(modifier = Modifier.fillMaxWidth().padding(0.dp, 5.dp, 0.dp, 5.dp)) {
        topRegions.forEach { selected ->
            item {
                Text(
                    modifier = Modifier.padding(10.dp, 0.dp).clickable {
                        if (topRegion != selected) {
                            topRegion = selected
                            onTagChanged(selected.tid)
                        }
                    }, text = selected.name
                )
            }
        }
    }
    LazyRow(modifier = Modifier.fillMaxWidth().padding(0.dp, 0.dp, 0.dp, 5.dp)) {
        topRegion.children.forEach { selected ->
            item {
                Text(
                    modifier = Modifier.padding(5.dp, 0.dp).clickable {
                        if (tid != selected.tid) {
                            tid = selected.tid
                            onTagChanged(tid)
                        }
                    }, text = selected.name
                )
            }
        }
    }
}