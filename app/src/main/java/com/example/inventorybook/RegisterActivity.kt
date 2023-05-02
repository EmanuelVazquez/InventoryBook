package com.example.inventorybook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.inventorybook.Modelos.User
import com.example.inventorybook.Repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {
    private lateinit var registerButton: Button
    private lateinit var cancelButton: Button
    private lateinit var emailInput: EditText
    private lateinit var passInput: EditText
    private lateinit var passConfirmInput: EditText
    private lateinit var nameInput: EditText
    private lateinit var lastNameInput: EditText
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        registerButton = findViewById(R.id.registerButton)
        cancelButton = findViewById(R.id.cancelRegisterButton)
        emailInput = findViewById(R.id.emailRegisterInput)
        passInput = findViewById(R.id.registerPassInput)
        passConfirmInput = findViewById((R.id.registerPassConfirmInput))
        nameInput = findViewById(R.id.nameRegisterInput)
        lastNameInput = findViewById(R.id.lastNameRegisterInput)
        setup()
    }

    private fun setup(){
        title = "Registro"
        registerButton.setOnClickListener{
            if(emailInput.text.isNotEmpty() && passInput.text.isNotEmpty() && passConfirmInput.text.isNotEmpty() && nameInput.text.isNotEmpty() && lastNameInput.text.isNotEmpty()){
                if(passInput.text.toString() == passConfirmInput.text.toString()){
                    FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(emailInput.text.toString(),passConfirmInput.text.toString()).addOnCompleteListener{
                            if(it.isSuccessful){
                                val userRepository = UserRepository()
                                val newUser = User(emailInput.text.toString(),nameInput.text.toString(), lastNameInput.text.toString())
                                userRepository.addUser(newUser)
                                Toast.makeText(this, "Registro con exito", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this,AuthActivity::class.java)
                                startActivity(intent)
                            }else{
                                showAlert("Se ha producido un error","Error")
                            }
                        }
                }
                else
                    showAlert("Las contraseñas no coinciden","Error")

            }
            else
                showAlert("Favor de llenar todos los campos","Error")
        }
        cancelButton.setOnClickListener {
            val intent = Intent(this,AuthActivity::class.java)
            startActivity(intent)
        }

    }
    private fun showAlert(msg: String, type: String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(type)
        builder.setMessage(msg)
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}