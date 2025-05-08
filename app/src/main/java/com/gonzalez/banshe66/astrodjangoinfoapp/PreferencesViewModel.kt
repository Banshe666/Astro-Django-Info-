package com.gonzalez.banshe66.astrodjangoinfoapp

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

// Extension property to create a DataStore instance tied to application context
private val Context.dataStore by preferencesDataStore(name = "user_prefs")

// ViewModel that manages user preferences such as name and surname
class PreferencesViewModel(application: Application) : AndroidViewModel(application) {

    private val dataStore = application.applicationContext.dataStore

    // UI states: name and surname fields, and whether fields are editable
    var name by mutableStateOf("")
    var surname by mutableStateOf("")
    var isEditable by mutableStateOf(true)

    // Load saved preferences when ViewModel is initialized
    init {
        viewModelScope.launch {
            val prefs = dataStore.data.first()
            name = prefs[stringPreferencesKey("name")] ?: ""
            surname = prefs[stringPreferencesKey("surname")] ?: ""
            isEditable = false
        }
    }

    // Save name and surname to DataStore and disable editing
    fun saveData() {
        viewModelScope.launch {
            dataStore.edit { prefs ->
                prefs[stringPreferencesKey("name")] = name
                prefs[stringPreferencesKey("surname")] = surname
            }
            isEditable = false
        }
    }

    // Enable editing of the name and surname fields
    fun enableEdit() {
        isEditable = true
    }
}
