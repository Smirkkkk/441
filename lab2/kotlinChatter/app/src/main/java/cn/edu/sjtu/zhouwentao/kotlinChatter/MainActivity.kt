package cn.edu.sjtu.zhouwentao.kotlinChatter

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import cn.edu.sjtu.zhouwentao.kotlinChatter.ChattStore.chatts
import cn.edu.sjtu.zhouwentao.kotlinChatter.ChattStore.getChatts
import cn.edu.sjtu.zhouwentao.kotlinChatter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var view: ActivityMainBinding
    private lateinit var chattListAdapter: ChattListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityMainBinding.inflate(layoutInflater)
        view.root.setBackgroundColor(Color.parseColor("#E0E0E0"))
        setContentView(view.root)

        chattListAdapter = ChattListAdapter(this, chatts)
        view.chattListView.setAdapter(chattListAdapter)
        chatts.addOnListChangedCallback(propertyObserver)
        // setup refreshContainer here later
        view.refreshContainer.setOnRefreshListener {
            getChatts()
        }

        getChatts()

    }
    fun startPost(view: View?) = startActivity(Intent(this, PostActivity::class.java))
    private fun refreshTimeline() {
        getChatts()

        // stop the refreshing animation upon completion:
        view.refreshContainer.isRefreshing = false
    }
    private val propertyObserver = object: ObservableList.OnListChangedCallback<ObservableArrayList<Int>>() {
        override fun onChanged(sender: ObservableArrayList<Int>?) { }
        override fun onItemRangeChanged(sender: ObservableArrayList<Int>?, positionStart: Int, itemCount: Int) { }
        override fun onItemRangeInserted(
            sender: ObservableArrayList<Int>?,
            positionStart: Int,
            itemCount: Int
        ) {
            println("onItemRangeInserted: $positionStart, $itemCount")
            runOnUiThread {
                chattListAdapter.notifyDataSetChanged()
            }
        }
        override fun onItemRangeMoved(sender: ObservableArrayList<Int>?, fromPosition: Int, toPosition: Int,
                                      itemCount: Int) { }
        override fun onItemRangeRemoved(sender: ObservableArrayList<Int>?, positionStart: Int, itemCount: Int) { }
    }
    override fun onDestroy() {
        super.onDestroy()

        chatts.removeOnListChangedCallback(propertyObserver)
    }
}