package com.example.simplescrambler.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Scramble::class], version = 1, exportSchema = false)
abstract class ScrambleDB : RoomDatabase() {
    abstract fun scrambleDao(): ScrambleDao

    companion object {
        @Volatile // 멀티 스레드 환경에서 일관된 값을 유지(변수의 최신 상태 보장)
        private var INSTANCE: ScrambleDB? = null
        private const val DB_NAME = "scramble_db"

        fun getInstance(context: Context) : ScrambleDB {
            // 동기화(멀티 스레드 환경에서 안전하게 생성(하나의 스레드만 접근 가능))
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, ScrambleDB::class.java, DB_NAME)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}