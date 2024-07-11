package cn.edu.sjtu.zhouwentao.kotlinJpCChatter

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cn.edu.sjtu.zhouwentao.kotlinJpCChatter.ChattStore.chatts
import cn.edu.sjtu.zhouwentao.kotlinJpCChatter.ChattStore.getChatts
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(context: Context, navController: NavHostController) {
    var isLaunching by rememberSaveable { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        if (isLaunching) {
            isLaunching = false
            getChatts(context)
        }
    }
    var isRefreshing by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = stringResource(R.string.chatter),
                    fontSize = 20.sp
                )
            })
        },
        floatingActionButton = {
            FloatingActionButton(
                contentColor = Color(0xFF00FF00),
                modifier = Modifier.padding(0.dp, 0.dp, 8.dp, 8.dp),
                onClick = {
                    // navigate to PostView
                    navController.navigate("PostView")
                }
            ) {
                Icon(Icons.Default.Add, stringResource(R.string.post))
            }
        },
        content = {padding ->
            // content of Scaffold
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing),
                modifier = Modifier.background(color = Color(0xFFEFEFEF)),
                onRefresh = {
                    getChatts(context)
                    isRefreshing = false
                }
            ) {
                // describe the View
                LazyColumn(
                    verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.padding(0.dp, 70.dp, 0.dp, 0.dp)
                        .background(color = Color(0xFFEFEFEF))
                ) {
                    items(count = chatts.size, key = { index -> chatts[index].timestamp ?: index }) { index ->
                        ChattListRow(index, chatts[index])
                    }
                }
            }
        }
    )
}