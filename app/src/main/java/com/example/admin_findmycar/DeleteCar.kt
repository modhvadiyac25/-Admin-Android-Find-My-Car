package com.example.admin_findmycar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_delete_car.*
import kotlinx.android.synthetic.main.activity_update_car.*

class DeleteCar : AppCompatActivity() {
    lateinit var Name : String
    lateinit var car_id:String
    var database: DatabaseReference = FirebaseDatabase.getInstance().getReference("cars")
    internal var storageReference : StorageReference? = FirebaseStorage.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_car)

        deleteCar.setOnClickListener {
            database.child(car_id!!).removeValue().addOnCompleteListener {task->
                if(task.isSuccessful){
                    Toast.makeText(this,Name + " is Deleted",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,"" + task.exception,Toast.LENGTH_LONG).show()
                }
            }
        }

        var cars : ArrayList<String> = ArrayList()
        cars.clear()

        var getdata = object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                //var sb = StringBuilder()
                for (i in snapshot.children) {
                    cars.add(i.child("").child("name").getValue().toString())
                }
            }
        }

        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)

        val adapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, cars)

        list_view_delete.adapter = adapter
        search_delete.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search_delete.clearFocus()
                if (cars.contains(query)) {
                    adapter.filter.filter(query)
                    fetchCarData(query!!)
//                    var intent = Intent(this, CarDetails::class.java)
//                    intent.putExtra("Brand", query)
//                    startActivity(intent)
                    // Toast.makeText(this@Search," " + query + " <-->" + adapter.filter.filter(query),Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@DeleteCar, "Item not Found", Toast.LENGTH_LONG).show()
                }
                return false
            }
            override fun onQueryTextChange(query: String?): Boolean {
                adapter.filter.filter(query)
                return false
            }
        })
        list_view_delete.setOnItemClickListener { parent, view, position, id ->
            //  view.text
//            Toast.makeText(this@DeleteCar , " parent : " + parent + "\nView : " +view+"\nPosition : " + position+"\nid : "+ id,
//                Toast.LENGTH_LONG ).show()
        }


    }
    private fun fetchCarData(query: String) {
        var getdata = object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                //var sb = StringBuilder()
                for (i in snapshot.children) {
                    if (query.equals(i.child("name").getValue())) {

                        car_id = i.child("cid").getValue().toString()
                        var Brand = i.child("brand").getValue().toString()
                        var Model = i.child("model").getValue().toString()
                        Name = i.child("name").getValue().toString()


                        //load car image from the firebase
                        //loaddata to load json data into activity
                        var warning_msg :String = "Do you want to delete "+Name+" ? "
                        findViewById<TextView>(R.id.warning).setText(warning_msg)
                        warning.visibility = View.VISIBLE
                        deleteCar.visibility = View.VISIBLE

                    }
                }
            }
        }
        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)
    }
}
