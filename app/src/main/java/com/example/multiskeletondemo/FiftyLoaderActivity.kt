package com.example.multiskeletondemo

import android.os.Bundle
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.multiskeletondemo.fiftyshadesof.FiftyShadesOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FiftyLoaderActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val fiftyShades3 = FiftyShadesOf.with(this)
    private val fiftyShades2 = FiftyShadesOf.with(this)
    private val fiftyShades = FiftyShadesOf.with(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifty_loader)
        recyclerView = findViewById(R.id.fl_rv_movement_list);
        initRecyclerView()

        fiftyShades.setRadius(25)
            .on(
                R.id.tv_tv_home_quick_access_sell_with_QR,
                R.id.tv_tv_home_quick_access_sell_with_link,
                R.id.tv_tv_home_quick_access_sell_with_mPOS
            )
        fiftyShades2.setRadius(90)
            .on(
                R.id.tv_loaderImageView,
                R.id.tv_loaderImageView2
            ).on(R.id.tv_loaderImageView3)

        GlobalScope.launch(context = Dispatchers.Main) {
            delay(5000)
            fiftyShades.stop()
            fiftyShades2.stop()
        }
    }

    private fun onStartSkeleton(){
        fiftyShades3.setRadius(25).start()
        fiftyShades2.start()
        fiftyShades.start()
    }

    private fun initRecyclerView() {
        val adapter = FiftyRecyclerViewAdapter(fiftyShades3)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                onStartSkeleton()
                recyclerView.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }
}
