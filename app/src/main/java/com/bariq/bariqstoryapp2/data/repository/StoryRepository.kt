package com.bariq.bariqstoryapp2.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.*
import com.bariq.bariqstoryapp2.data.Result
import com.bariq.bariqstoryapp2.data.StoryRemoteMediator
import com.bariq.bariqstoryapp2.data.database.StoryDatabase
import com.bariq.bariqstoryapp2.data.response.AddStoryResponse
import com.bariq.bariqstoryapp2.data.response.GetStoriesResponse
import com.bariq.bariqstoryapp2.data.response.ListStoryItem
import com.bariq.bariqstoryapp2.retrofit.ApiService
import okhttp3.MultipartBody
import okhttp3.RequestBody

class StoryRepository(private val apiService: ApiService, private val storyDatabase: StoryDatabase) {

    fun getStories(token: String) : LiveData<PagingData<ListStoryItem>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator = StoryRemoteMediator(storyDatabase, apiService, token),
            pagingSourceFactory = {
                storyDatabase.storyDao().getAllStories()
            }
        ).liveData
    }

    fun addStories(token: String, photo: MultipartBody.Part, desc: RequestBody, lat: RequestBody?, lon: RequestBody?) : LiveData<Result<AddStoryResponse>> = liveData {
        emit(Result.Loading)
        try {
            val result = apiService.addStory("Bearer $token", photo, desc, lat, lon)
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getMaps(token: String) : LiveData<Result<GetStoriesResponse>> = liveData {
        emit(Result.Loading)
        try {
            val result = apiService.getStories("Bearer $token", location = 1)
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }
}