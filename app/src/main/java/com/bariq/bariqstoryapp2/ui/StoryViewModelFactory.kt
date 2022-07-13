package com.bariq.bariqstoryapp2.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bariq.bariqstoryapp2.data.repository.StoryRepository
import com.bariq.bariqstoryapp2.data.repository.UserRepository
import com.bariq.bariqstoryapp2.di.StoryInjection
import com.bariq.bariqstoryapp2.di.UserInjection
import com.bariq.bariqstoryapp2.ui.main.MainViewModel
import com.bariq.bariqstoryapp2.ui.maps.MapsViewModel
import com.bariq.bariqstoryapp2.ui.story.StoryViewModel

class StoryViewModelFactory private constructor(private val userRepository: UserRepository, private val storyRepository: StoryRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(userRepository, storyRepository) as T
            }
            modelClass.isAssignableFrom(StoryViewModel::class.java) -> {
                StoryViewModel(storyRepository) as T
            }
            modelClass.isAssignableFrom(MapsViewModel::class.java) -> {
                MapsViewModel(storyRepository) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
            }
        }
    }

    companion object {
        @Volatile
        private var instance: StoryViewModelFactory? = null
        fun getInstance(context: Context): StoryViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: StoryViewModelFactory(UserInjection.providePreferences(context), StoryInjection.provideRepository(context))
            }.also { instance = it }
    }
}