package com.example.sampleapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sampleapp.data.local.dao.PostsDao
import com.example.sampleapp.data.local.entities.Posts

@Database(entities = [Posts::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postsDao(): PostsDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "AppDb")
                .fallbackToDestructiveMigration()
                .build()
    }

}