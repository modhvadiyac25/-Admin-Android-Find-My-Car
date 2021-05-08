package com.example.admin_findmycar

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
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

                    if(email.equals("findmycar2021@gmail.com") && password.equals("abc123")){

                        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener {
                            Toast.makeText(
                                this@AdminLogin,
                                 "You are Logged In succesfully !!",
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

//        btn_forgot_pass.setOnClickListener {
//
//            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
//            builder.setTitle("Forgot Password")
//            val view = layoutInflater.inflate(R.layout.dialig_forget_password, null)
//            builder.setView(view)
//            val username =  view.findViewById<EditText>(R.id.et_username)
//
//            builder.setPositiveButton("Reset", DialogInterface.OnClickListener { _, _ ->
//                forgotPassword(username)
//            })
//            builder.setNegativeButton("Close", DialogInterface.OnClickListener { _, _ ->
//
//            })
//            builder.show()
//        }

    }

//    fun forgotPassword(username: EditText) {
//
//        var email = username.text.toString().trim()
//        if (email.isEmpty()) { //username.text.toString()
//            return
//        }
//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            return
//        }
//
//        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    Toast.makeText(this , "Email sent.", Toast.LENGTH_LONG).show()
//                    // Log.d(TAG, "Email sent.")
//                }
//                else{
//                    Toast.makeText(this ,task.exception.toString(), Toast.LENGTH_LONG).show()
//                }
//            }
//    }
}