package com.aohanyao.bouncingjelly

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar

import com.aohanyao.bouncingjelly.adapter.RecyclerAdapter
import com.aohanyao.jelly.library.ui.BouncingRecyclerView

class ListActivity : AppCompatActivity() {

    private var rl: BouncingRecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val toolbar = findViewById(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)
        rl = findViewById(R.id.rl) as BouncingRecyclerView?
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val mAdapter = RecyclerAdapter(ListActivity@this)
        val layoutManager = LinearLayoutManager(ListActivity@this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rl!!.layoutManager = layoutManager
        rl!!.adapter = mAdapter
    }

}
