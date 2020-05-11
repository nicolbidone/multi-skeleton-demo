package com.example.multiskeletondemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.multiskeletondemo.fiftyshadesof.FiftyShadesOf

class FiftyLoaderActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var data = mutableListOf<String?>(null, null, null, null, null, null, null, null, null, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifty_loader)
        recyclerView = findViewById(R.id.fl_rv_movement_list);
        initRecyclerView()

        FiftyShadesOf.with(this)
            .setRadius(25)
            .on(
                R.id.tv_tv_home_quick_access_sell_with_QR,
                R.id.tv_tv_home_quick_access_sell_with_link,
                R.id.tv_tv_home_quick_access_sell_with_mPOS
            )
            .start()
        FiftyShadesOf.with(this)
            .setRadius(90)
            .on(
                R.id.tv_loaderImageView,
                R.id.tv_loaderImageView2,
                R.id.tv_loaderImageView3
            )
            .start()
//        GlobalScope.launch(context = Dispatchers.Main) {
//            delay(5000)
//        }
    }

    private fun initRecyclerView() {
        val adapter = RecyclerViewAdapter(){}
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
