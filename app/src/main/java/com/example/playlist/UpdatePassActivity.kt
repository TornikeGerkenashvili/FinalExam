package com.example.playlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_update_pass.*

class UpdatePassActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_pass)

        auth = FirebaseAuth.getInstance()

        changePass.setOnClickListener {
            val password: String = inputPassword.text.toString()

            if (TextUtils.isEmpty(password)){
                Toast.makeText(this, "Enter The Password!", Toast.LENGTH_LONG).show()
            }else{
                auth.currentUser?.updatePassword(password)
                    ?.addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful){
                            Toast.makeText(this, "Password Changed", Toast.LENGTH_LONG).show()
                            finish()
                        }else{
                            Toast.makeText(this, "Something Wrong", Toast.LENGTH_LONG).show()
                        }

                    })
            }
        }

        exitBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}
