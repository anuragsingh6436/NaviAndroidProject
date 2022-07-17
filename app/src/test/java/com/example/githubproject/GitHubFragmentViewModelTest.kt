package com.example.githubproject

import com.example.githubproject.event.ActivityConstants
import com.example.githubproject.event.ActivityConstants.USER_REPO
import com.example.githubproject.repository.GitHubProjectRepository
import com.example.githubproject.viewModel.GitHubProjectFragmentViewModel
import com.example.githubproject.viewModel.GitHubProjectViewModel
import io.mockk.MockKAnnotations
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(InstantExecutorExtension::class)
class GitHubFragmentViewModelTest {

    val repository = mockk<GitHubProjectRepository>()
    val mainActivityViewModel = GitHubProjectFragmentViewModel(repository)

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init()
        mockkCoroutine()
    }

    @Test
    fun testUrl() {
        val url = mainActivityViewModel.getUrl()
        Assert.assertEquals(url, "${ActivityConstants.USER_NAME}/$USER_REPO/pulls?state=closed")
    }

    @Test
    fun testApiError() {
        mainActivityViewModel.handleApiError()
        Assert.assertEquals(mainActivityViewModel.enableRetryButton.get(),true)
    }

    @Test
    fun testRetryClick() {
        mockkCoroutine()
        mainActivityViewModel.retryButtonClick()
        Assert.assertEquals(mainActivityViewModel.toggleProgressBar.get(),true)
        Assert.assertEquals(mainActivityViewModel.enableRetryButton.get(),false)
        unMockkCoroutine()
    }

    @After
    fun tearDown() {
        unmockkAll()
        unMockkCoroutine()
    }
}