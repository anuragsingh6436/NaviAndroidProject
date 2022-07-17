package com.example.githubproject.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.githubproject.R
import com.example.githubproject.event.ActivityEvent
import com.example.githubproject.model.Event
import com.example.githubproject.viewModel.GitHubProjectViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GitHubProjectActivity : AppCompatActivity() {

    private val viewModel: GitHubProjectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git_hub_project)
        viewModel.eventStream.observe(this) {
            handleEvents(it)
        }

        openGitHubFragment()
    }

    private fun handleEvents(event: Event) {
        when (event.id) {
            ActivityEvent.API_ERROR -> {
                Toast.makeText(this, "No Response From Api..Please retry", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun openGitHubFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, GitHubProjectFragment.getInstnace(), GitHubProjectFragment.TAG)
            .addToBackStack(GitHubProjectFragment.TAG).commitAllowingStateLoss()
    }

    override fun onBackPressed() {
        finish()
    }

}