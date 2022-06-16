package com.example.githubproject.network

import android.util.Log
import com.example.githubproject.model.response.ClosedPullRequestList

class ApiHelper(private val apiService: ApiService) {

    suspend fun getClosedPullRequest(url: String): List<ClosedPullRequestList> {
        return apiService.getClosedPullRequests(url)
    }
}