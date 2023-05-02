package com.example.inventorybook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        emailEditText = findViewById(R.id.inputEmailText)
        passwordEditText = findViewById(R.id.inputPassword)
    }

    fun accessMenu(view: View) {
        val email = emailEditText.text.toString()
        val pass = passwordEditText.text.toString()

        if(email.isNotEmpty() && pass.isNotEmpty()){
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email,pass).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent = Intent(this,MenuActivity::class.java)
                        startActivity(intent)
                    }else{
                        showAlert()
                    }
                }
        }
    }
    fun registerAccess(view: View){
        val intent = Intent(this,RegisterActivity::class.java)
        startActivity(intent)
    }
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun showHome(email: String, provider: ProviderType){
        val homeIntent = Intent(this, MenuActivity::class.java).apply {
            putExtra("email",email)
            putExtra("provider", provider.name)
        }
    }
}