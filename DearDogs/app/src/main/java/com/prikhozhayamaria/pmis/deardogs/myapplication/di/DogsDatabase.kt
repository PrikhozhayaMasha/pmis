package com.prikhozhayamaria.pmis.deardogs.myapplication.di

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.data.source.DogsDao
import java.util.Date

@Database(entities = [DogEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DogsDatabase : RoomDatabase() {

    abstract fun dogsDao(): DogsDao
}

object Converters {
    @TypeConverter
    fun timestampToDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?) = date?.time
}