package com.example.githubproject.viewModel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubproject.adapter.MainAdapter
import com.example.githubproject.dataModel.MainActivityItemDataModel
import com.example.githubproject.event.ActivityEvent
import com.example.githubproject.model.Event
import com.example.githubproject.model.response.ClosedPullRequestList
import com.example.githubproject.repository.MainActivityRepository
import com.example.githubproject.ui.MainActivity
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
    val itemsList = ObservableArrayList<MainActivityItemDataModel>()

    init {
        getDataFromRepo()
    }

    private fun getDataFromRepo() {
        viewModelScope.launch {
            val data = repository.getClosePullRequest(getUrl())
                .catch {
                    eventStream.postValue(Event(ActivityEvent.CLOSE_ACTIVITY))
                }
                .collect {
                    handleResponse(it)
                }
        }
    }

    private fun handleResponse(data: List<ClosedPullRequestList>) {
        data.forEach {
            itemsList.add(MainActivityItemDataModel(it, eventStream))
        }
    }

    private fun getUrl(): String {
        return "${MainActivity.USER_NAME}/${MainActivity.USER_REPO}/pulls?state=closed"
    }
}