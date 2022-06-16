package com.example.githubproject.binding

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubproject.adapter.MainAdapter
import com.example.githubproject.dataModel.MainActivityItemDataModel
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
    adapter: MainAdapter,
    itemsList: ArrayList<MainActivityItemDataModel>
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