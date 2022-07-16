package com.example.githubproject

import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@ExperimentalCoroutinesApi
private val testDispatcher = TestCoroutineDispatcher()

@ExperimentalCoroutinesApi
fun mockkCoroutine(){
    mockkStatic(Dispatchers::class)
    every { Dispatchers.IO } returns testDispatcher
    every { Dispatchers.Default } returns testDispatcher
    every { Dispatchers.Unconfined } returns testDispatcher
    Dispatchers.setMain(testDispatcher)
}

@ExperimentalCoroutinesApi
fun unMockkCoroutine(){
    testDispatcher.cleanupTestCoroutines()
    Dispatchers.resetMain()
    unmockkStatic(Dispatchers::class)
}