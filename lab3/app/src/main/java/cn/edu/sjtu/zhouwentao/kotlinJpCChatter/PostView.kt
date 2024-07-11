package cn.edu.sjtu.zhouwentao.kotlinJpCChatter

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cn.edu.sjtu.zhouwentao.kotlinJpCChatter.ChattStore.postChatt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostView(context: Context, navController: NavHostController) {
    val username = stringResource(R.string.username)
    var message by rememberSaveable { mutableStateOf(context.getString(R.string.message)) }
    var enableSend by rememberSaveable { mutableStateOf(true) }

    Scaffold(
        // put the topBar here
        topBar = { TopAppBar(title = { Text(text = stringResource(R.string.post),
            fontSize=20.sp) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() } ) {
                    Icon(painter = painterResource(com.google.android.material.R.drawable.abc_ic_ab_back_material),
                        stringResource(R.string.chatter))
                }
            },
            actions = { IconButton(onClick = {
                enableSend = false
                postChatt(context, Chatt(username, message))
                navController.popBackStack("MainView", inclusive = false)
            }, enabled = enableSend) {
                Icon(painter = painterResource(android.R.drawable.ic_menu_send), stringResource(R.string.send))
            } }) },
        content = {padding->
            Column(
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp)
            ) {
                Text(username, Modifier.padding(0.dp, 30.dp, 0.dp, 0.dp)
                    .fillMaxWidth(1f), textAlign= TextAlign.Center, fontSize = 20.sp)
                TextField(
                    value = message,
                    onValueChange = { message = it },
                    modifier = Modifier.padding(8.dp, 20.dp, 8.dp, 0.dp).fillMaxWidth(1f),
                    textStyle = TextStyle(fontSize = 17.sp)
                )
            }
        }
    )
}