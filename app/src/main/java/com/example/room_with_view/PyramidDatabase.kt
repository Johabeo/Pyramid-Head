package com.example.room_with_view

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Pyramid::class], version = 2, )
abstract class PyramidDatabase : RoomDatabase() {

    abstract fun pyramidDao(): PyramidDao

    companion object {
        @Volatile
        private var INSTANCE: PyramidDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): PyramidDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PyramidDatabase::class.java,
                    "pyramid_database"
                )
                    .addCallback(PyramidDatabaseCallback(scope))
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class PyramidDatabaseCallback(
            private val scope:CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let {database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.pyramidDao())
                    }
                }
            }
        }


        suspend fun populateDatabase(pyramidDao: PyramidDao) {
            pyramidDao.deleteALL()
            var pyramid = Pyramid (4,"thing", 2)
            pyramidDao.insert(pyramid)
            pyramid = Pyramid (5,"triangle", 2)
            pyramidDao.insert(pyramid)
            pyramid = Pyramid (6,"pizza", 2)
            pyramidDao.insert(pyramid)
        }
    }
}