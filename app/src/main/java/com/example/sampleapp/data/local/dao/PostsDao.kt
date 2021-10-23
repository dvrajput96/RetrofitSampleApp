package com.example.sampleapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sampleapp.data.local.entities.Posts

@Dao
interface PostsDao {

    @Query("SELECT * FROM posts")
    fun getPosts(): LiveData<List<Posts>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg posts: Posts)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(posts: Posts)

    /* @Query("SELECT * FROM characters WHERE id = :id")
     fun getCharacter(id: Int): LiveData<Character>

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertAll(characters: List<Character>)

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insert(character: Character)*/


}