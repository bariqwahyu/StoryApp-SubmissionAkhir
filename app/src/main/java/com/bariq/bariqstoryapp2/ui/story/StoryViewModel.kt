package com.bariq.bariqstoryapp2.ui.story

import androidx.lifecycle.ViewModel
import com.bariq.bariqstoryapp2.data.repository.StoryRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody

class StoryViewModel(private val storyRepository: StoryRepository) : ViewModel() {
    fun addStory(token: String, photo: MultipartBody.Part, desc: RequestBody, lat: RequestBody?, lon: RequestBody?) = storyRepository.addStories(token, photo, desc, lat, lon)
}