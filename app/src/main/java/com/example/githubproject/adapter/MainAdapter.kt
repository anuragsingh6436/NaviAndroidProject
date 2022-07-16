package com.example.githubproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubproject.R
import com.example.githubproject.databinding.ActivityMainItemBinding
import com.example.githubproject.model.response.ClosedPullRequestList

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private val itemsList = ArrayList<ClosedPullRequestList>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding: ActivityMainItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.activity_main_item, parent, false)
        return MainViewHolder(dataBinding.root, dataBinding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.onBindData(itemsList[position], position)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    fun setItems(list: ArrayList<ClosedPullRequestList>) {
        itemsList.addAll(list)
        notifyDataSetChanged()
    }
}

class MainViewHolder(itemview: View, val dataBinding: ActivityMainItemBinding) :
    RecyclerView.ViewHolder(itemview) {

    fun onBindData(item: ClosedPullRequestList, position: Int) {
        with(dataBinding) {
            data = item
            executePendingBindings()
        }
    }
}

