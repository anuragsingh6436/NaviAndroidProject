package com.example.githubproject.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.githubproject.R
import com.example.githubproject.adapter.VerticalItemDecorator
import com.example.githubproject.databinding.ActivityMainBinding
import com.example.githubproject.event.ActivityEvent
import com.example.githubproject.model.Event
import com.example.githubproject.viewModel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        const val USER_NAME = "sdras"
        const val USER_REPO = "intro-to-vue"
    }

    private val viewModel: MainActivityViewModel by viewModels()
    lateinit var viewDataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewDataBinding.viewModel = viewModel

        viewModel.eventStream.observe(this) {
            handleEvents(it)
        }

        setItemDecorator()
    }

    private fun setItemDecorator() {
        with(viewDataBinding) {
            recyclerView.addItemDecoration(VerticalItemDecorator(20))
        }
    }

    private fun handleEvents(event: Event) {
        when (event.id) {
            ActivityEvent.CLOSE_ACTIVITY -> {
                Toast.makeText(this, "No Response From Api", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

}