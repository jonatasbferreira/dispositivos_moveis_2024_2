package com.example.msg.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.msg.data.local.dao.MessageDao
import com.example.msg.model.Message


@Database(entities = [Message::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
}