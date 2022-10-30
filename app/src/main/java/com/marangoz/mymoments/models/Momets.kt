package com.marangoz.mymoments.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "rehber_tbl")
data class Momets(
    @ColumnInfo(name = "mText") @NotNull var mText: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "mId") var mId: Int? = null
) {


}