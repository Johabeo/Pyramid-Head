package com.example.room_with_view

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PyramidDao {
    @Query("SELECT * FROM pyramid_table ORDER BY height ASC")
    fun getPyramids(): Flow<List<Pyramid>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pyramid: Pyramid)

    @Query("DELETE FROM pyramid_table")
    suspend fun deleteALL()
}