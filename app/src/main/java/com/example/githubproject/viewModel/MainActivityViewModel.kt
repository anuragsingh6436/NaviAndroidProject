package com.example.githubproject.viewModel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubproject.adapter.MainAdapter
import com.example.githubproject.event.ActivityConstants.USER_NAME
import com.example.githubproject.event.ActivityConstants.USER_REPO
import com.example.githubproject.event.ActivityEvent
import com.example.githubproject.model.Event
import com.example.githubproject.model.response.ClosedPullRequestList
import com.example.githubproject.repository.MainActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(val repository: MainActivityRepository) :
    ViewModel() {

    val eventStream = MutableLiveData<Event>()
    val adapter = MainAdapter()
    val itemsList = ObservableArrayList<ClosedPullRequestList>()
    val enableRetryButton = ObservableBoolean(false)
    val toggleProgressBar = ObservableBoolean(true)

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
        itemsList.addAll(data)
    }

     fun getUrl(): String {
        return "${USER_NAME}/$USER_REPO/pulls?state=closed"
    }

    fun retryButtonClick() {
        toggleProgressBar.set(true)
        enableRetryButton.set(false)
        getDataFromRepo()
    }
}