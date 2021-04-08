package com.example.admin_findmycar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
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
                    fetchCarData(query!!)
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
          //  view.text
            Toast.makeText(this@UpdateCar , " parent : " + parent + "\nView : " +view+"\nPosition : " + position+"\nid : "+ id,Toast.LENGTH_LONG ).show()
        }
    }

    private fun fetchCarData(query: String) {
        var getdata = object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                //var sb = StringBuilder()
                for (i in snapshot.children) {
                    if (query.equals(i.child("Brand").getValue())) {

                        var car_id = i.child("cid").getValue().toString()
                        var just_launched = i.child("just_launched").getValue().toString()
                        var popular_car = i.child("popular_car").getValue().toString()
                        var top_10_cars = i.child("top_10_cars").getValue().toString()
                        var upcomming_cars = i.child("upcomming_cars").getValue().toString()
                        var Brand = i.child("Brand").getValue().toString()
                        var bodyType = i.child("bodyType").getValue().toString()
                        var Could_Be_Better = i.child("Could_Be_Better").getValue().toString()
                        var Description = i.child("Description").getValue().toString()
                        var Emission_Standard = i.child("Emission_Standard").getValue().toString()
                        var Engine = i.child("Engine").getValue().toString()
                        var Engine_Type = i.child("Engine_Type").getValue().toString()
                        var Fuel_Tank_Capacity = i.child("Fuel_Tank_Capacity").getValue().toString()
                        var Fuel_Type = i.child("Fuel_Type").getValue().toString()
                        var Good_Things = i.child("Good_Things").getValue().toString()
                        var Height = i.child("Height").getValue().toString()
                        var ImageUrl = i.child("ImageUrl").getValue().toString()
                        var Length = i.child("Length").getValue().toString()
                        var Max_Power = i.child("Max_Power").getValue().toString()
                        var Max_Torque = i.child("Max_Torque").getValue().toString()
                        var Mileage = i.child("Mileage").getValue().toString()
                        var Model = i.child("Model").getValue().toString()
                        var Name = i.child("Name").getValue().toString()
                        var Price= i.child("Price").getValue().toString()
                        var Seating_Capacity= i.child("Seating_Capacity").getValue().toString()
                        var Transmission_Type= i.child("Transmission_Type").getValue().toString()
                        var Width= i.child("Width").getValue().toString()
                        var GC = i.child("Ground_Clearance").getValue().toString()
                        var DriveTrain = i.child("DriveTrain").getValue().toString()
                        var Bootspace = i.child("Bootspace").getValue().toString()
                        var Verdict = i.child("Verdict").getValue().toString()
                        var color1 = i.child("colors").child("c1").getValue().toString()
                        var color2 = i.child("colors").child("c2").getValue().toString()
                        var color3 = i.child("colors").child("c3").getValue().toString()
                        var color4 = i.child("colors").child("c4").getValue().toString()
                        var color5 = i.child("colors").child("c5").getValue().toString()

                        //load car image from the firebase
                        var imageref = FirebaseStorage.getInstance().reference.child("cars/" + car_id + ".png" )
                        imageref.downloadUrl.addOnSuccessListener { Uri ->

                            val imageURL = Uri.toString()
                            var IV = findViewById<ImageView>(R.id.car_img)
                            Glide.with(this@UpdateCar)
                                .load(imageURL)
                                .into(IV)
                        }
                        //loaddata to load json data into activity
                        findViewById<EditText>(R.id.cname).setText(Name)
                        findViewById<EditText>(R.id.brand).setText(Brand)
                        findViewById<EditText>(R.id.model).setText(Model)
                        findViewById<EditText>(R.id.engine_type).setText(Engine_Type)
                        findViewById<EditText>(R.id.body_type).setText(bodyType)
                        findViewById<EditText>(R.id.JLC).setText(just_launched)
                        findViewById<EditText>(R.id.PC).setText(popular_car)
                        findViewById<EditText>(R.id.T10C).setText(top_10_cars)
                        findViewById<EditText>(R.id.UC).setText(upcomming_cars)
                        findViewById<EditText>(R.id.description).setText(Description)

                        findViewById<EditText>(R.id.price).setText(Price)

                        findViewById<EditText>(R.id.length).setText(Length)
                        findViewById<EditText>(R.id.height).setText(Height)
                        findViewById<EditText>(R.id.width).setText(Width)
                        findViewById<EditText>(R.id.ground_clearance).setText(GC)

                        findViewById<EditText>(R.id.engine).setText(Engine)
                        findViewById<EditText>(R.id.fueltype).setText(Fuel_Type)
                        findViewById<EditText>(R.id.maxpower).setText(Max_Power)
                        findViewById<EditText>(R.id.maxtorque).setText(Max_Torque)
                        findViewById<EditText>(R.id.mileage).setText(Mileage)
                        findViewById<EditText>(R.id.drive_trains).setText(DriveTrain)
                        findViewById<EditText>(R.id.transmissiontype).setText(Transmission_Type)
                        findViewById<EditText>(R.id.emission_standared).setText(Emission_Standard)

                        //Capacity
                        findViewById<EditText>(R.id.seating_capacity).setText(Seating_Capacity)
                        findViewById<EditText>(R.id.bootspace).setText(Bootspace)
                        findViewById<EditText>(R.id.FTC).setText(Fuel_Tank_Capacity)

                        //Colors
                        findViewById<EditText>(R.id.colors).setText(color1 +","+color2 +","+color3 +","+color4 +","+color5)
//                        findViewById<EditText>(R.id).setText(color2)
//                        findViewById<EditText>(R.id).setText(color3)
//                        findViewById<EditText>(R.id).setText(color4)
//                        findViewById<EditText>(R.id).setText(color5)

                        //good things
                        findViewById<EditText>(R.id.good_things).setText(Good_Things)

                        //could be better
                        findViewById<EditText>(R.id.CBB).setText(Could_Be_Better)

                        //verdict
                        findViewById<EditText>(R.id.verdict).setText(Verdict)
                    }
                }
            }
        }
        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)

    }
}
