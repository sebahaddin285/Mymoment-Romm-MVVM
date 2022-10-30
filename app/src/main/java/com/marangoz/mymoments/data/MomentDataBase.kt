package com.marangoz.mymoments.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.marangoz.mymoments.models.Momets
import kotlinx.coroutines.InternalCoroutinesApi

@Database(entities = [Momets::class], version = 1)
abstract class MomentDataBase : RoomDatabase(){
    abstract fun getMomentDao() : MomentDao // kisilerdao interface üzerindeki fonksiyonlara erişmek için kullanılır

    companion object{
        @Volatile
        var INSTANCE : MomentDataBase? = null

        fun accsessDatabase(context : Context) : MomentDataBase?{
            if (INSTANCE == null){
                synchronized(MomentDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MomentDataBase::class.java, "rehber.sqlite")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }


}