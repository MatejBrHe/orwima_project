package hr.ferit.matejbreznickiherceg.ricinglibrary

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import hr.ferit.matejbreznickiherceg.ricinglibrary.data.Rice
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter

class RiceViewModel(val riceList: List<Rice>): ViewModel(){
    private val originalData = MutableStateFlow(riceList)
    var riceData = riceList

    fun searchData(title: String){

    }
}