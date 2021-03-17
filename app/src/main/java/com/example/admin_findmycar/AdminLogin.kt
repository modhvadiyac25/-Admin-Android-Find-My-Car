package com.example.admin_findmycar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_admin_login.*

class AdminLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)

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

                        Toast.makeText(
                            this@AdminLogin,
                            "You are Logged In succesfully !!",
                            Toast.LENGTH_SHORT
                        ).show()

                        val intent =
                            Intent(this@AdminLogin, MainActivity::class.java)
                        // while trafering one activity to another, it will create new layer so to remove that unused layer  FLAGE_ACTIVITY_CLEAR_TASK is used
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.putExtra(
                            "user_id",
                            FirebaseAuth.getInstance().currentUser!!.uid
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


//                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
//                        .addOnCompleteListener(
//                            { task ->
//
//                                if (task.isSuccessful) {
//
//                                    //    val firebaseuser: FirebaseUser = task.result!!.user!!
//
//                                    Toast.makeText(
//                                        this@AdminLogin,
//                                        "You are Logged In succesfully !!",
//                                        Toast.LENGTH_SHORT
//                                    ).show()
//
//                                    val intent =
//                                        Intent(this@AdminLogin, MainActivity::class.java)
//                                    // while trafering one activity to another, it will create new layer so to remove that unused layer  FLAGE_ACTIVITY_CLEAR_TASK is used
//                                    intent.flags =
//                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                                    intent.putExtra(
//                                        "user_id",
//                                        FirebaseAuth.getInstance().currentUser!!.uid
//                                    )
//                                    intent.putExtra("email_id", email)
//
//                                    startActivity(intent)
//                                    finish()
//
//                                } else {
//                                    Toast.makeText(
//                                        this@AdminLogin,
//                                        task.exception!!.message.toString(),
//                                        Toast.LENGTH_SHORT
//                                    ).show()
//                                }
//                            })
                }
            }
        }

    }
}