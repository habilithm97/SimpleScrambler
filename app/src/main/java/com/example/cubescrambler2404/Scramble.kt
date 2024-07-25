package com.example.cubescrambler2404

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scramble_tb")
data class Scramble(val scramble: String, val date: String) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}