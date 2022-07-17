package com.example.githubproject.binding

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubproject.adapter.GitHubProjectAdapter
import com.example.githubproject.adapter.VerticalItemDecorator
import com.example.githubproject.base.BaseRecyclerItem
import com.example.githubproject.model.response.ClosedPullRequestList
import com.squareup.picasso.Picasso

@BindingAdapter(value = ["imageUrl"], requireAll = false)
fun getImage(view: AppCompatImageView, imageUrl: String) {
    if (imageUrl.isNotEmpty()) {
        Picasso.get()
            .load(imageUrl)
            .into(view)
    }
}

@BindingAdapter(value = ["adapter", "itemsList"], requireAll = true)
fun updateRecyclerView(
    recyclerView: RecyclerView,
    adapter: GitHubProjectAdapter,
    itemsList: ArrayList<BaseRecyclerItem>
) {
    if (recyclerView.adapter == null) recyclerView.adapter = adapter
    if (itemsList.isNotEmpty()) {
        adapter.setItems(itemsList)
    }
}

@BindingAdapter(value = ["visibleGoneByText"], requireAll = true)
fun toggleViewByText(view: View, text: String?) {
    if (text.isNullOrEmpty()) View.GONE else View.VISIBLE
}

@BindingAdapter(value = ["visibleGone"], requireAll = true)
fun toggleView(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter(value = ["addItemDecorator"], requireAll = true)
fun addItemDecorator(recyclerView: RecyclerView, verticalHeight: Int) {
    recyclerView.addItemDecoration(VerticalItemDecorator(verticalHeight))
}