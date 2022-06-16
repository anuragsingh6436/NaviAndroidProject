package com.example.githubproject.repository

import com.example.githubproject.model.response.ClosedPullRequestList
import com.example.githubproject.network.ApiHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class MainActivityRepository(private val apiHelper: ApiHelper) {

    suspend fun getClosePullRequest(url: String): Flow<List<ClosedPullRequestList>> {
        return flow{
            emit(apiHelper.getClosedPullRequest(url))
        }
    }
}