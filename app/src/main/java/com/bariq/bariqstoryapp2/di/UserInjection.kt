package com.bariq.bariqstoryapp2.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.bariq.bariqstoryapp2.data.repository.UserRepository
import com.bariq.bariqstoryapp2.retrofit.ApiConfig

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

object UserInjection {
    fun providePreferences(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(context.dataStore, apiService)
    }
}