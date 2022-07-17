package com.example.githubproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.githubproject.R
import com.example.githubproject.base.BaseAdapter
import com.example.githubproject.base.BaseRecyclerItem
import com.example.githubproject.base.BaseViewHolder
import com.example.githubproject.databinding.FragmentGitHubProjectItemBinding
import com.example.githubproject.databinding.LoadingItemBinding
import com.example.githubproject.viewHolder.GitHubItemViewHolder
import com.example.githubproject.viewHolder.LoadingViewHolder

class GitHubProjectAdapter : BaseAdapter() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BaseRecyclerItem> {
        val layoutInflater = LayoutInflater.from(parent.context)
        return getViewHolder(viewType,layoutInflater,parent)
    }

     private fun getViewHolder(viewType: Int, layoutInflater: LayoutInflater, parent: ViewGroup): BaseViewHolder<BaseRecyclerItem> {
        when(viewType) {
            RecyclerViewItemTypes.GIT_HUB_ITEM_TYPE -> {
                val dataBinding: FragmentGitHubProjectItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_git_hub_project_item, parent, false)
                return GitHubItemViewHolder(dataBinding)
            }
            else ->  {
                val dataBinding: LoadingItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.loading_item, parent, false)
                return LoadingViewHolder(dataBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BaseRecyclerItem>, position: Int) {
        holder.onBindData(items[position],position)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

