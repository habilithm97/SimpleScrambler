package com.example.cubescrambler2404

import kotlinx.coroutines.flow.Flow

// dao를 사용하여 DB 작업을 캡슐화한 Repository 클래스
class MyRepository(private val roomDao: RoomDao) {
    suspend fun addScramble(scramble: Scramble) {
        roomDao.addScramble(scramble)
    }

    val getAll: Flow<List<Scramble>> = roomDao.getAll()
}