package com.example.githubproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubproject.R
import com.example.githubproject.dataModel.MainActivityItemDataModel
import com.example.githubproject.databinding.ActivityMainItemBinding

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private val itemsList = ArrayList<MainActivityItemDataModel>()

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

    fun setItems(list: ArrayList<MainActivityItemDataModel>) {
        itemsList.addAll(list)
        notifyDataSetChanged()
    }
}

class MainViewHolder(itemview: View, val dataBinding: ActivityMainItemBinding) :
    RecyclerView.ViewHolder(itemview) {

    fun onBindData(item: MainActivityItemDataModel, position: Int) {
        with(dataBinding) {
            viewModel = item
            executePendingBindings()
        }
    }
}

