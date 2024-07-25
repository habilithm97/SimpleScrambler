package com.example.cubescrambler2404

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addScramble(scramble: Scramble)

    @Query("select * from scramble_tb order by id")
    fun getAll(): Flow<List<Scramble>> // 비동기 데이터 스트림을 처리하기 위한 Flow
}