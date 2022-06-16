package com.example.githubproject.model.response

data class ClosedPullRequestList(
    val url: String,
    val id: Int,
    val title: String,
    val user: User,
    val created_at: String,
    val updated_at: String,
    val closed_at: String,
    val merged_at: String
)