package com.example.admin_findmycar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.header.*

class MainActivity : AppCompatActivity() {

    //for drawer layout
    lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) { 
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //onclick of drawer menu icon
        //drawermenu_icon.setOnClickListener() {

            //calling drawer menu
            //navMenu()
        //for drawer layout
        toggle = ActionBarDrawerToggle(this, drawerlayout, R.string.open, R.string.close)
        drawerlayout.addDrawerListener(toggle)

        // to connect this toggle with drawer layout
        toggle.syncState()
        // tv_email.text = intent.getStringExtra("email_id")

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navigation_view.setNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.login -> {
                    startActivity(
                        Intent(
                            this@MainActivity,
                            AdminLogin::class.java
                        )
                    )
                }

                R.id.add_car -> {
                    val intent =
                        Intent(this@MainActivity, AddCar::class.java)

                    startActivity(intent)
                }R.id.logout -> {
                    val intent =
                        Intent(this@MainActivity, AddCar::class.java)

                    startActivity(intent)
                }
                R.id.nav_updatecar -> {
//
                    val intent =
                        Intent(this@MainActivity, UpdateCar::class.java)
                    startActivity(intent)
//
//                    val intent =
//                        Intent(this@MainActivity, PopularCars::class.java)
//                    intent.putExtra(
//                        "user_id",
//                        FirebaseAuth.getInstance().currentUser!!.uid
//                    )
//                    startActivity(intent)
                }
                R.id.nav_populer_cars -> {
//
                    val intent =
                        Intent(this@MainActivity, GetAndSetImage::class.java)
                    startActivity(intent)
//
//                    val intent =
//                        Intent(this@MainActivity, PopularCars::class.java)
//                    intent.putExtra(
//                        "user_id",
//                        FirebaseAuth.getInstance().currentUser!!.uid
//                    )
//                    startActivity(intent)
                }

                R.id.nav_car_details -> {
//                    val intent =
//                        Intent(this@MainActivity, CarDetails::class.java)
//                    intent.putExtra(
//                        "user_id",
//                        FirebaseAuth.getInstance().currentUser!!.uid
//                    )
//                    startActivity(intent)
                }

                R.id.EMI -> {
//                    val intent =
//                        Intent(this@MainActivity,  CarDetails::class.java)
//                    intent.putExtra(
//                        "user_id",
//                        FirebaseAuth.getInstance().currentUser!!.uid
//                    )
//                    startActivity(intent)
                }
            }
            true
        }
    }

    //UDF function for drawerlayout
//    fun navMenu() {
//        //for drawer layout
//        toggle = ActionBarDrawerToggle(this, drawerlayout, R.string.open, R.string.close)
//        drawerlayout.addDrawerListener(toggle)
//
//        // to connect this toggle with drawer layout
//        toggle.syncState()
//       // tv_email.text = intent.getStringExtra("email_id")
//
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        navigation_view.setNavigationItemSelectedListener {
//            when (it.itemId) {
//
//                R.id.login -> {
//                    startActivity(
//                        Intent(
//                            this@MainActivity,
//                            AdminLogin::class.java
//                        )
//                    )
//                }
//
//                R.id.add_car -> {
//                    val intent =
//                        Intent(this@MainActivity, AddCar::class.java)
//
//                    startActivity(intent)
//                }
//                R.id.nav_populer_cars -> {
////                    val intent =
////                        Intent(this@MainActivity, PopularCars::class.java)
////                    intent.putExtra(
////                        "user_id",
////                        FirebaseAuth.getInstance().currentUser!!.uid
////                    )
////                    startActivity(intent)
//                }
//
//                R.id.nav_car_details -> {
////                    val intent =
////                        Intent(this@MainActivity, CarDetails::class.java)
////                    intent.putExtra(
////                        "user_id",
////                        FirebaseAuth.getInstance().currentUser!!.uid
////                    )
////                    startActivity(intent)
//                }
//
//                R.id.EMI -> {
////                    val intent =
////                        Intent(this@MainActivity,  CarDetails::class.java)
////                    intent.putExtra(
////                        "user_id",
////                        FirebaseAuth.getInstance().currentUser!!.uid
////                    )
////                    startActivity(intent)
//                }
//            }
//            true
//        }
 //   }
}
