package com.example.githubproject.viewModel

import com.example.githubproject.adapter.RecyclerViewItemTypes
import com.example.githubproject.base.BaseRecyclerItem

class LoadingViewModel : BaseRecyclerItem {

    override fun getItemtype(): Int {
        return RecyclerViewItemTypes.LOADING_ITEM
    }
}