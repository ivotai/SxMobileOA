package com.unicorn.sxmobileoa.n.process.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.github.vipulasri.timelineview.TimelineView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.n.process.model.Process


class ProcessAdapter : RecyclerView.Adapter<ProcessViewHolder>() {

    private var mData: List<Process> = ArrayList()
    private var mContext: Context? = null

    fun setNewData(data: List<Process>) {
        mData = data
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return TimelineView.getTimeLineViewType(position, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProcessViewHolder {
        mContext = parent.context
        val mLayoutInflater = LayoutInflater.from(mContext)
        val view = mLayoutInflater.inflate(R.layout.item_process, parent, false)
        return ProcessViewHolder(view, viewType)
    }

    override fun onBindViewHolder(holder: ProcessViewHolder, position: Int) {
        val timeLineModel = mData[position]
        holder.mTimelineView.setMarker(ContextCompat.getDrawable(mContext!!, R.drawable.ic_marker), ContextCompat.getColor(mContext!!, R.color.colorPrimary))
        holder.mMessage.text = "111111"
    }

    override fun getItemCount(): Int {
        return mData.size
    }

}
