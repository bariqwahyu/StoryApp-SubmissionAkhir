package com.bariq.bariqstoryapp2.ui.maps

import androidx.lifecycle.ViewModel
import com.bariq.bariqstoryapp2.data.repository.StoryRepository

class MapsViewModel(private val storyRepository: StoryRepository) : ViewModel() {
    fun getStoriesWithMaps(token: String) = storyRepository.getMaps(token)
}