package com.example.playlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_pass.*

class ForgotPassActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)

        auth = FirebaseAuth.getInstance()

        resetPass.setOnClickListener {
            val email: String = inputEmail.text.toString()

            if (TextUtils.isEmpty(email)){
                Toast.makeText(this, "Enter The Email", Toast.LENGTH_LONG).show()
            }else{
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this,
                                "Password Link Sent To Your Email",
                                Toast.LENGTH_LONG
                            ).show()
                        }else{
                            Toast.makeText(this, "Something Wrong", Toast.LENGTH_LONG).show()
                        }
                    })

            }
        }

        exitBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}
