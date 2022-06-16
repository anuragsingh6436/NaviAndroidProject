package com.example.githubproject.dataModel

import androidx.lifecycle.MutableLiveData
import com.example.githubproject.model.Event
import com.example.githubproject.model.response.ClosedPullRequestList

class MainActivityItemDataModel(
    val data: ClosedPullRequestList,
    val eventStream: MutableLiveData<Event>
) {

}