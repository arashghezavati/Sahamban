package com.example.android.sahamban.Detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.sahamban.Network.SahambanProperty

class DetailViewModelFactory(
    private val sahambanProperty: SahambanProperty,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(sahambanProperty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}