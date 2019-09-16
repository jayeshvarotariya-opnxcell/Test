package com.openxcell.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.openxcell.data.pojo.UserModel


@Database(entities = [UserModel::class],version = 1,exportSchema = false)
@TypeConverters(MyTypeConverters::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun provideUserDao() : UserDao

}