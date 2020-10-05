package com.dukim.sqlite

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RoomMemo::class),version = 1, exportSchema = false)

//추상클래스생섣
abstract class RoomHelper:RoomDatabase() {
    abstract fun roomMemoDao():RoomMemoDao
}