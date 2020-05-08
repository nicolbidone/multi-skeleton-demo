package com.example.multiskeletondemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_loader_view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ElyneLoaderActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loader_view)

        recyclerView = findViewById(R.id.rv_movement_list);
        initRecyclerView()
        GlobalScope.launch(context = Dispatchers.Main) {
            delay(5000)
            loadResources()
        }
    }

    private fun loadResources() {
        loaderImageView.setImageResource(R.drawable.ic_launcher_background)
        loaderImageView2.setImageResource(R.drawable.ic_launcher_background)
        loaderImageView3.setImageResource(R.drawable.ic_launcher_background)
        tv_home_quick_access_sell_with_QR.text = getString(R.string.home_quick_access_sell_with_qr)
        tv_home_quick_access_sell_with_mPOS.text = getString(R.string.home_quick_access_sell_with_mobile_point_of_sale)
        tv_home_quick_access_sell_with_link.text = getString(R.string.home_quick_access_sell_with_payment_button)
    }

    private fun initRecyclerView() {
        val adapter = RecyclerViewAdapter {}
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
