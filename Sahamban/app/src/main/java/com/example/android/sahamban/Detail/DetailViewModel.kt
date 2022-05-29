package com.example.android.sahamban.Detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.sahamban.Network.SahambanProperty

class DetailViewModel(sahambanProperty: SahambanProperty, app:Application): AndroidViewModel(app){

    private val _selectedProperty = MutableLiveData<SahambanProperty>()
    val selectedProperty:LiveData<SahambanProperty>
    get() = _selectedProperty

    init {
        _selectedProperty.value = sahambanProperty
    }
}