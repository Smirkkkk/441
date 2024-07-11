package cn.edu.sjtu.zhouwentao.kotlinJpCChatter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChattListRow(index: Int, chatt: Chatt) {
    Column(modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp)
        .background(color = Color(if (index % 2 == 0) 0xFFE0E0E0 else 0xFFEEEEEE))) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier=Modifier.fillMaxWidth(1f)) {
            chatt.username?.let { Text(it, fontSize = 17.sp, modifier = Modifier.padding(4.dp, 8.dp, 4.dp, 0.dp)) }

            chatt.timestamp?.let { Text(it, fontSize = 14.sp, textAlign = TextAlign.End, modifier = Modifier.padding(4.dp, 8.dp, 4.dp, 0.dp)) }
        }

        chatt.message?.let { Text(it, fontSize = 17.sp, modifier = Modifier.padding(4.dp, 10.dp, 4.dp, 10.dp)) }
    }
}