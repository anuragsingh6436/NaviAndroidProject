package com.example.githubproject.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter: RecyclerView.Adapter<BaseViewHolder<BaseRecyclerItem>>() {
    val items = ArrayList<BaseRecyclerItem>()

    abstract override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<BaseRecyclerItem>

    fun setItems(items:ArrayList<BaseRecyclerItem>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun removeItem(item: BaseRecyclerItem) {
        if( !items.contains(item) || items.size == 0)
            return
        val position: Int = items.indexOf(item)
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].getItemtype()
    }
}