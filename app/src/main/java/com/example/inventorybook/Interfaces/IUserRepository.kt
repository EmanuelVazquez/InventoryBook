package com.example.inventorybook.Interfaces

import androidx.lifecycle.MutableLiveData
import com.example.inventorybook.Modelos.User

interface IUserRepository {
    fun addUser(user: User)
    fun getUser(id: String): User?
    fun updateUser()
    fun getAll(): MutableLiveData<List<User>>
}