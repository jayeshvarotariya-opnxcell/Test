package com.openxcell.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.openxcell.data.pojo.UserModel


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(vararg user: UserModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(users: List<UserModel>)

    @Delete
    fun deleteUser(user: UserModel)

    @Update
    fun updateUser(user: UserModel)


    @Query("Select * from UserModel")
    fun getAllList() : LiveData<List<UserModel>>

}