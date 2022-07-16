package com.example.githubproject.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubproject.model.response.ClosedPullRequestList
import com.example.githubproject.network.ApiService
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1


//class ApiPagingSource(
//    private val service: ApiService
//) : PagingSource<Int, ClosedPullRequestList>() {
//
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ClosedPullRequestList> {
//        val pageIndex = params.key ?: STARTING_PAGE_INDEX
//        return try {
//            val response = service.getClosedPullRequests()
//            val movies = response.results
//            val nextKey =
//                if (movies.isEmpty()) {
//                    null
//                } else {
//                    // By default, initial load size = 3 * NETWORK PAGE SIZE
//                    // ensure we're not requesting duplicating items at the 2nd request
//                    pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
//                }
//            LoadResult.Page(
//                data = movies,
//                prevKey = if (pageIndex == STARTING_PAGE_INDEX) null else pageIndex,
//                nextKey = nextKey
//            )
//        } catch (exception: IOException) {
//            return LoadResult.Error(exception)
//        } catch (exception: HttpException) {
//            return LoadResult.Error(exception)
//        }
//    }
//
//    /**
//     * The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
//     */
//    override fun getRefreshKey(state: PagingState<Int, ClosedPullRequestList>): Int? {
//        // We need to get the previous key (or next key if previous is null) of the page
//        // that was closest to the most recently accessed index.
//        // Anchor position is the most recently accessed index.
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }
//}