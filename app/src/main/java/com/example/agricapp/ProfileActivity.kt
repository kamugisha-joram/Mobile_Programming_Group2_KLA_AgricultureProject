package com.example.agricapp

import android.content.Intent
<<<<<<< HEAD
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {
=======
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var storage: FirebaseStorage
    
    private lateinit var profileImage: ImageView
    private lateinit var editName: EditText
    private lateinit var editPhone: EditText
    private lateinit var progressBar: ProgressBar
    
    private var selectedImageUri: Uri? = null

>>>>>>> Robert
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

<<<<<<< HEAD
=======
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance()

        profileImage = findViewById(R.id.profileImage)
        editName = findViewById(R.id.editName)
        editPhone = findViewById(R.id.editPhone)
        progressBar = findViewById(R.id.progressBar)

        val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                selectedImageUri = it
                profileImage.setImageURI(it)
            }
        }

        findViewById<View>(R.id.editImageBtn).setOnClickListener {
            pickImage.launch("image/*")
        }

        findViewById<View>(R.id.saveBtn).setOnClickListener {
            saveProfile()
        }

        loadProfileData()
        setupBottomNavigation()
    }

    private fun loadProfileData() {
        val user = auth.currentUser ?: return
        
        editName.setText(user.displayName)
        findViewById<EditText>(R.id.profileEmail).setText(user.email)
        
        // Load photo if exists
        user.photoUrl?.let {
            Glide.with(this).load(it).placeholder(R.drawable.ic_person).into(profileImage)
        }

        // Load extra data (Phone) from Firestore
        db.collection("users").document(user.uid).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    editPhone.setText(document.getString("phone"))
                }
            }
    }

    private fun saveProfile() {
        val name = editName.text.toString().trim()
        val phone = editPhone.text.toString().trim()
        val user = auth.currentUser ?: return

        if (name.isEmpty()) {
            Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        progressBar.visibility = View.VISIBLE

        if (selectedImageUri != null) {
            uploadImageAndSaveData(name, phone)
        } else {
            updateAuthAndFirestore(name, phone, user.photoUrl?.toString())
        }
    }

    private fun uploadImageAndSaveData(name: String, phone: String) {
        val user = auth.currentUser ?: return
        // Use a unique name for the image based on user ID
        val ref = storage.reference.child("profile_pics/${user.uid}.jpg")

        ref.putFile(selectedImageUri!!)
            .addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener { uri ->
                    updateAuthAndFirestore(name, phone, uri.toString())
                }.addOnFailureListener { e ->
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, "Failed to get URL: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener { e ->
                progressBar.visibility = View.GONE
                Toast.makeText(this, "Storage upload failed: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }

    private fun updateAuthAndFirestore(name: String, phone: String, photoUrl: String?) {
        val user = auth.currentUser ?: return

        // 1. Update Firebase Auth (Name & Photo)
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(name)
            .setPhotoUri(if (photoUrl != null) Uri.parse(photoUrl) else null)
            .build()

        user.updateProfile(profileUpdates).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // 2. Update Firestore (Phone & PhotoUrl)
                val userData = hashMapOf(
                    "name" to name,
                    "phone" to phone,
                    "photoUrl" to photoUrl,
                    "email" to user.email
                )

                db.collection("users").document(user.uid).set(userData)
                    .addOnSuccessListener {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, "Profile updated successfully!", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, "Firestore update failed: ${e.message}", Toast.LENGTH_LONG).show()
                    }
            } else {
                progressBar.visibility = View.GONE
                Toast.makeText(this, "Auth update failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupBottomNavigation() {
>>>>>>> Robert
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_profile

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, DashboardActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_messages -> {
                    startActivity(Intent(this, MessagesActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_notifications -> {
                    startActivity(Intent(this, NotificationsActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_profile -> true
                else -> false
            }
        }
    }
}
