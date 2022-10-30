package com.marangoz.mymoments.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.marangoz.mymoments.data.MomentDao
import com.marangoz.mymoments.models.Momets
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class MomentRepo(val mDao: MomentDao,val notesrepo : MutableLiveData<List<Momets>>) {


    suspend fun getNotes(){
        notesrepo.value = mDao.allMoments()
    }

    suspend fun insertNotes(motet: Momets) {

        mDao.insertMoment(motet)
        notesrepo.value = mDao.allMoments()
    }


    suspend fun deleteNotes(motet: Momets) {
        mDao.deleteMoment(motet)
        notesrepo.value = mDao.allMoments()
    }


}