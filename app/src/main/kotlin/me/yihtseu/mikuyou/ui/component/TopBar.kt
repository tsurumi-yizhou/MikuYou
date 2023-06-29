package me.yihtseu.mikuyou.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Icon(
    val pic: ImageVector,
    val action: () -> Unit
)

@Composable
fun TopBar(title: String, buttons: List<Icon> = listOf()) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(0.dp, 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Row {
            buttons.forEach {
                IconButton(
                    modifier = Modifier,
                    onClick = it.action
                ) {
                    Icon(it.pic, null)
                }
            }
        }
    }
}