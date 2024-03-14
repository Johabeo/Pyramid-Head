package com.example.room_with_view

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class PyramidRepository(private val pyramidDao: PyramidDao) {

    val allPyramids: Flow<List<Pyramid>> = pyramidDao.getPyramids()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(pyramid: Pyramid) {
        pyramidDao.insert(pyramid)
    }

}