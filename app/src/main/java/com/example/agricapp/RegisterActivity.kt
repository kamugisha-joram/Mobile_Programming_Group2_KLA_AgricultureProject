package com.example.agricapp


import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

  lateinit var auth: FirebaseAuth

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_register)

    auth = FirebaseAuth.getInstance()

    val email = findViewById<EditText>(R.id.email)
    val password = findViewById<EditText>(R.id.password)
    val btn = findViewById<Button>(R.id.registerBtn)

    btn.setOnClickListener {
      val userEmail = email.text.toString()
      val userPass = password.text.toString()

      if (userEmail.isEmpty() || userPass.isEmpty()) {
        Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
        return@setOnClickListener
      }

      auth.createUserWithEmailAndPassword(userEmail, userPass)
        .addOnCompleteListener {
          if (it.isSuccessful) {
            Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show()
            finish()
          } else {
            Toast.makeText(this, "Error: ${it.exception?.message}", Toast.LENGTH_LONG).show()
          }
        }
    }
  }
}