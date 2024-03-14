package com.example.room_with_view

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pyramid_table")
data class Pyramid(@PrimaryKey(autoGenerate = true)val id: Int =0, @ColumnInfo(name = "key") val key: String,
                   @ColumnInfo(name = "height") val height: Int?
)
