package com.example.cubescrambler2404

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Scramble::class], version = 1, exportSchema = false)
abstract class RoomDB : RoomDatabase() {
    abstract fun roomDao(): RoomDao

    companion object { // 싱글톤
        @Volatile // 싱글톤 보장(해당 데이터가 메인 메모리에만 존재하도록 명시)
        private var INSTANCE: RoomDB? = null
        private const val DB_NAME = "scramble_db"

        fun getInstance(context: Context) : RoomDB {
            return INSTANCE ?: synchronized(this) { // 동기화(멀티 스레드 환경에서 안전하게 생성)
                val instance = Room.databaseBuilder(
                    context.applicationContext, RoomDB::class.java, DB_NAME)
                    .build()
                INSTANCE = instance // 싱글톤 유지
                instance
            }
        }
    }
}