package com.bariq.bariqstoryapp2.di

import android.content.Context
import com.bariq.bariqstoryapp2.data.database.StoryDatabase
import com.bariq.bariqstoryapp2.data.repository.StoryRepository
import com.bariq.bariqstoryapp2.retrofit.ApiConfig

object StoryInjection {
    fun provideRepository(context: Context): StoryRepository {
        val apiService = ApiConfig.getApiService()
        val database = StoryDatabase.getDatabase(context)
        return StoryRepository(apiService, database)
    }
}