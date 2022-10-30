package com.marangoz.mymoments.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.marangoz.mymoments.models.Momets

@Dao
interface MomentDao {

    @Query("select * from rehber_tbl")
    suspend fun allMoments(): List<Momets>

    @Delete
    suspend fun deleteMoment(momets: Momets)

    @Insert
    suspend fun insertMoment(momets: Momets)


}