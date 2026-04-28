package com.example.agricapp


import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

  lateinit var auth: FirebaseAuth

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

    auth = FirebaseAuth.getInstance()

    val email = findViewById<EditText>(R.id.email)
    val password = findViewById<EditText>(R.id.password)
    val loginBtn = findViewById<Button>(R.id.loginBtn)
    val registerLink = findViewById<TextView>(R.id.registerLink)

    loginBtn.setOnClickListener {
      val userEmail = email.text.toString()
      val userPass = password.text.toString()

      if (userEmail.isEmpty() || userPass.isEmpty()) {
        Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
        return@setOnClickListener
      }

      auth.signInWithEmailAndPassword(userEmail, userPass)
        .addOnCompleteListener {
          if (it.isSuccessful) {
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
          } else {
            Toast.makeText(this, "Error: ${it.exception?.message}", Toast.LENGTH_LONG).show()
          }
        }
    }

    registerLink.setOnClickListener {
      startActivity(Intent(this, RegisterActivity::class.java))
    }
  }
}