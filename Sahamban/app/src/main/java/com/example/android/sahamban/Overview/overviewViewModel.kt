package com.example.android.sahamban.Overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.sahamban.Network.SahambanApiFilter
import com.example.android.sahamban.Network.SahambanProperty
import com.example.android.sahamban.Network.sahambanApi
import kotlinx.coroutines.launch
import java.lang.Exception


enum class SahambanApiStatus{LOADING,ERROR,DONE}
class overviewViewModel:ViewModel(){

    private val _properties = MutableLiveData<List<SahambanProperty>>()
   val properties:LiveData<List<SahambanProperty>>
   get() = _properties


    private val _status = MutableLiveData<SahambanApiStatus>()
    val status : LiveData<SahambanApiStatus>
    get() = _status


    private val _navigateToSelectedProperty = MutableLiveData<SahambanProperty>()
    val navigateToSelectedProperty:LiveData<SahambanProperty>
    get() = _navigateToSelectedProperty


    init {
        getResult(SahambanApiFilter.SHOW_ALL)
    }

    private fun getResult(filter: SahambanApiFilter) {

        viewModelScope.launch {
            _status.value=SahambanApiStatus.LOADING
            try {
                _properties.value = sahambanApi.retrofitService.getProperties(filter.value)
                _status.value = SahambanApiStatus.DONE
            }catch (e:Exception){
                _status.value = SahambanApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }
    fun displayPropertyDetails(sahambanProperty: SahambanProperty){
_navigateToSelectedProperty.value= sahambanProperty
    }

    fun displayPropertyDetailsCompelete(){
        _navigateToSelectedProperty.value=null
    }

    fun updateFilter(filter: SahambanApiFilter){
        getResult(filter)
    }
}

