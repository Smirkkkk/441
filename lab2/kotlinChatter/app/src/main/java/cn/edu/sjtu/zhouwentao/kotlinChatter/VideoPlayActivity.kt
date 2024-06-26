package cn.edu.sjtu.zhouwentao.kotlinChatter

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cn.edu.sjtu.zhouwentao.kotlinChatter.databinding.ActivityVideoPlayBinding
import cn.edu.sjtu.zhouwentao.kotlinChatter.ui.theme.KotlinChatterTheme

class VideoPlayActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ActivityVideoPlayBinding.inflate(layoutInflater)
        setContentView(view.root)

        view.videoView.setVideoURI(intent.getParcelableExtra("VIDEO_URI", Uri::class.java))

        with (MediaController(this)) {
            setAnchorView(view.videoView)
            view.videoView.setMediaController(this)
            view.videoView.setOnPreparedListener { show(0) }
        }
        view.videoView.setOnCompletionListener { finish() }
        view.videoView.start()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinChatterTheme {
        Greeting("Android")
    }
}