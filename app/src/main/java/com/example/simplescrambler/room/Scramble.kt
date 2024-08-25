package com.example.simplescrambler.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scramble")
data class Scramble(val scramble: String, val date: String) {
    @PrimaryKey(autoGenerate = true) var id = 0
}