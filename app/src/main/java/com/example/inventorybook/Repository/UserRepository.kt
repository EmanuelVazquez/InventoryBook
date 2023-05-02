package com.example.inventorybook.Repository

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.example.inventorybook.Interfaces.IUserRepository
import com.example.inventorybook.MenuActivity
import com.example.inventorybook.Modelos.User
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository: IUserRepository {

    private val database = FirebaseFirestore.getInstance()

    override fun addUser(user: User) {
        database.collection("users").document(user.email).set(
            hashMapOf("name" to user.name,
            "lastName" to user.lastName)
        )
    }

    override fun getUser(email: String): User? {
        var user: User? = null
        database.collection("user").document(email).get().addOnSuccessListener {
             user = User(email,it.get("name") as String,it.get("lastName") as String)
        }
        return user
    }

    override fun updateUser() {
        TODO("Not yet implemented")
    }

    override fun getAll(): MutableLiveData<List<User>> {
        TODO("Not yet implemented")
    }
}