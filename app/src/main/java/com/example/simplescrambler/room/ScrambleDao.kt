package com.example.simplescrambler.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScrambleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addScramble(scramble: Scramble)

    @Delete
    suspend fun deleteScramble(scramble: Scramble)

    @Query("delete from scramble")
    suspend fun deleteAll()

    @Query("select * from scramble order by id")
    fun getAll(): Flow<List<Scramble>> // 비동기 데이터 스트림을 처리하기 위한 Flow
}