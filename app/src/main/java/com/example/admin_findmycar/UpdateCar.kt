package com.example.admin_findmycar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_update_car.*

class UpdateCar : AppCompatActivity() {

    var database: DatabaseReference = FirebaseDatabase.getInstance().getReference("cars")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_car)

        var cars : ArrayList<String> = ArrayList()
        cars.clear()

        var getdata = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                //var sb = StringBuilder()
                for (i in snapshot.children) {
                    cars.add(i.child("").child("Brand").getValue().toString())

                }
            }
        }

        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)

        val adapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, cars)

        list_view.adapter = adapter
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search.clearFocus()
                if (cars.contains(query)) {
                    adapter.filter.filter(query)
//                    var intent = Intent(this, CarDetails::class.java)
//                    intent.putExtra("Brand", query)
//                    startActivity(intent)
                    // Toast.makeText(this@Search," " + query + " <-->" + adapter.filter.filter(query),Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@UpdateCar, "Item not Found", Toast.LENGTH_LONG).show()
                }
                return false
            }
            override fun onQueryTextChange(query: String?): Boolean {
                adapter.filter.filter(query)
                return false
            }
        })
        list_view.setOnItemClickListener { parent, view, position, id ->
            view.text
            Toast.makeText(this@UpdateCar , " parent : " + parent + "\nView : " +view+"\nPosition : " + position+"\nid : "+ id,Toast.LENGTH_LONG ).show()
        }

    }
}
