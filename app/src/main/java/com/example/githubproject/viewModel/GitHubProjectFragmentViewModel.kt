package com.example.githubproject.viewModel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.viewModelScope
import com.example.githubproject.adapter.GitHubProjectAdapter
import com.example.githubproject.base.BaseRecyclerItem
import com.example.githubproject.base.BaseViewModel
import com.example.githubproject.event.ActivityConstants
import com.example.githubproject.event.ActivityEvent
import com.example.githubproject.model.Event
import com.example.githubproject.model.response.ClosedPullRequestList
import com.example.githubproject.repository.GitHubProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitHubProjectFragmentViewModel @Inject constructor(val repository: GitHubProjectRepository) :
    BaseViewModel() {

    val adapter = GitHubProjectAdapter()
    val itemsList = ObservableArrayList<BaseRecyclerItem>()
    val enableRetryButton = ObservableBoolean(false)
    val toggleProgressBar = ObservableBoolean(true)
    val totalItems = ObservableArrayList<ClosedPullRequestList>()

    fun getDataFromRepo() {
        viewModelScope.launch {
            repository.getClosePullRequest(getUrl())
                .catch {
                    toggleProgressBar.set(false)
                    eventStream.postValue(Event(ActivityEvent.API_ERROR))
                    handleApiError()
                }
                .collect {
                    toggleProgressBar.set(false)
                    handleResponse(it)
                }
        }
    }

    fun handleApiError() {
        enableRetryButton.set(true)
    }


    fun handleResponse(data: List<ClosedPullRequestList>) {
        totalItems.addAll(data)
        addItemsInRecyclerView(0, ActivityConstants.MAX_PAGING_ITEMS)
    }

    fun getUrl(): String {
        return "${ActivityConstants.USER_NAME}/${ActivityConstants.USER_REPO}/pulls?state=closed"
    }

    fun retryButtonClick() {
        toggleProgressBar.set(true)
        enableRetryButton.set(false)
        getDataFromRepo()
    }

    fun addItemsInRecyclerView(startPos: Int, endPos: Int) {
        if (endPos >= totalItems.size) {
            adapter.removeItem(itemsList.last())
            itemsList.addAll(totalItems.subList(startPos, totalItems.size))
        } else {
            if(itemsList.size > 0)adapter.removeItem(itemsList.last())
            itemsList.addAll(totalItems.subList(startPos, endPos))
            itemsList.add(LoadingViewModel())
        }
    }
}