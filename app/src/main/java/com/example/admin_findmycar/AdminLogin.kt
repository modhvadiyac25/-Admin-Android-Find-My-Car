package com.example.admin_findmycar

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_admin_login.*

class AdminLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)

        //to hide action bar in login
        val actionBar: ActionBar? = supportActionBar
        actionBar!!.hide()


        btn_sign_in.setOnClickListener {
            // if the input field is empty then toast will promted
            when {

                TextUtils.isEmpty(email.text.toString().trim() { it <= ' ' }) -> {
                    Toast.makeText(this@AdminLogin, "Please Enter Email", Toast.LENGTH_SHORT)
                        .show()
                }

                TextUtils.isEmpty(password.text.toString().trim() { it <= ' ' }) -> {
                    Toast.makeText(
                        this@AdminLogin,
                        "Please Enter Password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {

                    val email: String = email.text.toString().trim() { it <= ' ' }
                    val password: String = password.text.toString().trim() { it <= ' ' }

                    if(email.equals("findmycar@gmail.com") && password.equals("abc123")){

                        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener {
                            Toast.makeText(
                                this@AdminLogin,
                                FirebaseAuth.getInstance().currentUser!!.uid +"You are Logged In succesfully !!",
                                Toast.LENGTH_SHORT
                            ).show()

                        }

                        val intent =
                            Intent(this@AdminLogin, MainActivity::class.java)

                        // while trafering one activity to another, it will create new layer so to remove that unused layer  FLAGE_ACTIVITY_CLEAR_TASK is used
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.putExtra(
                            "user_id",
                            "ACN52oMPENYHcegI8ZTD5PxFeko2"
                        )
                        intent.putExtra("email_id", email)

                        startActivity(intent)
                        finish()
                    }
                    else{
                        Toast.makeText(
                            this@AdminLogin,
                            "Oops !! Having Truble in Login !! ",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
            }
        }

    }
}