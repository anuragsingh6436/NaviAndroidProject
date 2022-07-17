package com.example.githubproject.viewHolder

import com.example.githubproject.base.BaseRecyclerItem
import com.example.githubproject.base.BaseViewHolder
import com.example.githubproject.databinding.FragmentGitHubProjectItemBinding
import com.example.githubproject.model.response.ClosedPullRequestList


class GitHubItemViewHolder(val dataBinding: FragmentGitHubProjectItemBinding) :
    BaseViewHolder<BaseRecyclerItem>(dataBinding.root) {

    override fun onBindData(item: BaseRecyclerItem, position: Int) {
        with(dataBinding) {
            data = item as ClosedPullRequestList
            executePendingBindings()
        }
    }
}

