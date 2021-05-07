package com.example.admin_findmycar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.header.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

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


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navigation_view.setNavigationItemSelectedListener {
            when (it.itemId) {



                R.id.add_car -> {
                    val intent =
                        Intent(this@MainActivity, AddCar::class.java)

                    startActivity(intent)
                }R.id.logout -> {
                    val intent =
                        Intent(this@MainActivity, AdminLogin::class.java)

                    startActivity(intent)
                }
                R.id.nav_updatecar -> {
                    val intent =
                        Intent(this@MainActivity, UpdateCar::class.java)
                    startActivity(intent)
                 }

            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(it: MenuItem): Boolean {
        TODO("Not yet implemented")
    }




}
