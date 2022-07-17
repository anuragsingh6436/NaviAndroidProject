package com.example.githubproject.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubproject.model.Event

open class BaseViewModel : ViewModel() {

    val eventStream = MutableLiveData<Event>()

}