package com.example.githubproject.viewHolder

import android.view.View
import com.example.githubproject.base.BaseRecyclerItem
import com.example.githubproject.base.BaseViewHolder
import com.example.githubproject.databinding.LoadingItemBinding
import com.example.githubproject.viewModel.LoadingViewModel


class LoadingViewHolder(val dataBinding: LoadingItemBinding) :
    BaseViewHolder<BaseRecyclerItem>(dataBinding.root) {

    override fun onBindData(item: BaseRecyclerItem, position: Int) {
        with(dataBinding) {
            viewModel = item as LoadingViewModel
            executePendingBindings()
        }
    }
}