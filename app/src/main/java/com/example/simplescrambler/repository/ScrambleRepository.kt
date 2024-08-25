package com.example.simplescrambler.repository

import com.example.simplescrambler.room.Scramble
import com.example.simplescrambler.room.ScrambleDao
import kotlinx.coroutines.flow.Flow

// Dao를 사용하여 DB 작업을 캡슐화
class ScrambleRepository(private val scrambleDao: ScrambleDao) {

    suspend fun addScramble(scramble: Scramble) {
        scrambleDao.addScramble(scramble)
    }

    suspend fun deleteScramble(scramble: Scramble) {
        scrambleDao.deleteScramble(scramble)
    }

    suspend fun deleteAll() {
        scrambleDao.deleteAll()
    }

    val getAll: Flow<List<Scramble>> = scrambleDao.getAll()
}