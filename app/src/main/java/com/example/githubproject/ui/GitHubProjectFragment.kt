package com.example.githubproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubproject.R
import com.example.githubproject.databinding.FragmentGitHubProjectBinding
import com.example.githubproject.event.ActivityEvent
import com.example.githubproject.model.Event
import com.example.githubproject.viewModel.GitHubProjectFragmentViewModel
import com.example.githubproject.viewModel.GitHubProjectViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GitHubProjectFragment : Fragment() {

    companion object {
        const val TAG = "GitHubProjectFragment"
        fun getInstnace(): GitHubProjectFragment {
            return GitHubProjectFragment()
        }
    }

    lateinit var viewModel: GitHubProjectFragmentViewModel
    lateinit var dataBinding: FragmentGitHubProjectBinding

    private val activityViewModel by lazy { activityViewModels<GitHubProjectViewModel>() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(
            GitHubProjectFragmentViewModel::class.java
        )
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_git_hub_project, container, false)
        dataBinding.viewModel = viewModel
        viewModel.eventStream.observe(viewLifecycleOwner) {
            handleEvents(it)
        }
        viewModel.getDataFromRepo()
        /*
        added scroll listener to check if we reached at the bottom of reyclerview
        to handle paging and load new items in recyclerview
         */
        dataBinding.recyclerView.addOnScrollListener(scrollListener)
        return dataBinding.root
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val scrollDownWards = dy > 0
            val lastItemReached = with(layoutManager) { childCount + findFirstVisibleItemPosition() >= itemCount }
            if(scrollDownWards && lastItemReached) {
                viewModel.addItemsInRecyclerView(layoutManager.itemCount,layoutManager.itemCount+5)
            }
        }
    }

    private fun handleEvents(event: Event) {
        when (event.id) {
            ActivityEvent.API_ERROR -> {
                activityViewModel.value.eventStream.postValue(event)
            }
        }
    }
}