package com.example.multiskeletondemo

import android.os.Bundle
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.multiskeletondemo.fiftyshadesof.FiftyShadesOf
import kotlinx.android.synthetic.main.activity_fifty_loader.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FiftyLoaderActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val fiftyShades3 = FiftyShadesOf.with(this)
    private val fiftyShades2 = FiftyShadesOf.with(this)
    private val fiftyShades = FiftyShadesOf.with(this)
    private val data = mutableListOf("data","data","data","data","data","data")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifty_loader)
        recyclerView = findViewById(R.id.rv_activity_fifty_loader);
        initRecyclerView()

        fiftyShades.setRadius(25)
            .on(
                R.id.tv_activity_fifty_loader_left,
                R.id.tv_activity_fifty_loader_right,
                R.id.tv_activity_fifty_loader_center
            )
        fiftyShades2.setRadius(90)
            .on(
                R.id.iv_activity_fifty_loader_left,
                R.id.iv_activity_fifty_loader_center
            ).on(R.id.iv_activity_fifty_loader_right)

        GlobalScope.launch(context = Dispatchers.Main) {
            delay(5000)
            fiftyShades.stop()
            fiftyShades2.stopAnd(){
            Glide.with(this@FiftyLoaderActivity)
                .load(getDrawable(R.drawable.ic_launcher_background))
                .into(iv_activity_fifty_loader_center)
            tv_activity_fifty_loader_center.text = "loaded"
            data[2] = "dog"
            recyclerView.adapter?.notifyItemChanged(2)
            }

        }
    }

    private fun onStartSkeleton() {
        fiftyShades3.setRadius(25).start()
        fiftyShades2.start()
        fiftyShades.start()
    }

    private fun initRecyclerView() {
        val adapter = FiftyRecyclerViewAdapter(fiftyShades3, data)
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
