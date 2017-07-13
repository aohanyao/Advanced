package com.aohanyao.bouncingjelly.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.aohanyao.bouncingjelly.R

/**
 *
 * 作者：江俊超 on 2016/8/31 11:00
 *
 * 邮箱：928692385@qq.com
 *
 *
 */
class RecyclerAdapter(private val mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_list_item, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 13
    }

    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
