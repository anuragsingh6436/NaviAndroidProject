package com.example.githubproject.model.response

import com.example.githubproject.adapter.RecyclerViewItemTypes
import com.example.githubproject.base.BaseRecyclerItem

data class ClosedPullRequestList(
    val url: String,
    val id: Int,
    val title: String,
    val user: User,
    val created_at: String,
    val updated_at: String,
    val closed_at: String,
    val merged_at: String
) : BaseRecyclerItem {

    override fun getItemtype(): Int {
        return RecyclerViewItemTypes.GIT_HUB_ITEM_TYPE
    }

}