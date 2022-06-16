package com.example.githubproject.network

import com.example.githubproject.model.response.ClosedPullRequestList
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getClosedPullRequests(@Url url: String): List<ClosedPullRequestList>
}