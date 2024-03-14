package com.example.room_with_view

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class PyramidApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy {
        PyramidDatabase.getDatabase(this, applicationScope)
    }
    val repository by lazy { PyramidRepository(database.pyramidDao()) }
}