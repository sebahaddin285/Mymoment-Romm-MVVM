package com.marangoz.mymoments.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.marangoz.mymoments.data.MomentDao
import com.marangoz.mymoments.models.Momets
import com.marangoz.mymoments.repository.MomentRepo
import kotlinx.coroutines.launch


class MainViewModel(
    private val mDao: MomentDao
) : ViewModel() {

    var notes = MutableLiveData<List<Momets>>()
    val repo = MomentRepo(mDao,notes)

    fun getNotes() {
        viewModelScope.launch {
           repo.getNotes()
        }
    }

    fun insertNotes(motet: Momets) {
        viewModelScope.launch {
            repo.insertNotes(motet)
        }
    }

    fun deleteNotes(motet: Momets) {
        viewModelScope.launch {
            repo.deleteNotes(motet)
        }
    }

}

class MainViewModelFactory(
    private val mDao: MomentDao
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(mDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

