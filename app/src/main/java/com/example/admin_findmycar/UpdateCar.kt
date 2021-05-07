package com.example.admin_findmycar

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_add_car.*
import kotlinx.android.synthetic.main.activity_update_car.*
import kotlinx.android.synthetic.main.activity_update_car.CBB
import kotlinx.android.synthetic.main.activity_update_car.FTC
import kotlinx.android.synthetic.main.activity_update_car.JLC
import kotlinx.android.synthetic.main.activity_update_car.PC
import kotlinx.android.synthetic.main.activity_update_car.T10C
import kotlinx.android.synthetic.main.activity_update_car.UC
import kotlinx.android.synthetic.main.activity_update_car.body_type
import kotlinx.android.synthetic.main.activity_update_car.bootspace
import kotlinx.android.synthetic.main.activity_update_car.brand
import kotlinx.android.synthetic.main.activity_update_car.car_img
import kotlinx.android.synthetic.main.activity_update_car.cname
import kotlinx.android.synthetic.main.activity_update_car.colors
import kotlinx.android.synthetic.main.activity_update_car.description
import kotlinx.android.synthetic.main.activity_update_car.drive_trains
import kotlinx.android.synthetic.main.activity_update_car.emission_standared
import kotlinx.android.synthetic.main.activity_update_car.engine
import kotlinx.android.synthetic.main.activity_update_car.engine_type
import kotlinx.android.synthetic.main.activity_update_car.fueltype
import kotlinx.android.synthetic.main.activity_update_car.good_things
import kotlinx.android.synthetic.main.activity_update_car.ground_clearance
import kotlinx.android.synthetic.main.activity_update_car.height
import kotlinx.android.synthetic.main.activity_update_car.length
import kotlinx.android.synthetic.main.activity_update_car.maxpower
import kotlinx.android.synthetic.main.activity_update_car.maxtorque
import kotlinx.android.synthetic.main.activity_update_car.mileage
import kotlinx.android.synthetic.main.activity_update_car.model
import kotlinx.android.synthetic.main.activity_update_car.price
import kotlinx.android.synthetic.main.activity_update_car.seating_capacity
import kotlinx.android.synthetic.main.activity_update_car.transmissiontype
import kotlinx.android.synthetic.main.activity_update_car.verdict
import kotlinx.android.synthetic.main.activity_update_car.width
import java.io.IOException
import java.util.regex.Pattern

class UpdateCar : AppCompatActivity(), View.OnClickListener  {
    lateinit var car_id:String
    var database: DatabaseReference = FirebaseDatabase.getInstance().getReference("cars")
    internal var storageReference : StorageReference? = FirebaseStorage.getInstance().reference
    lateinit var imageUri : Uri
    private var filePath : Uri? =   null
    val PIC_IMAGE_REQUEST = 1234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_car)

        update_btnchoose.setOnClickListener(this)

        btn_UpdateCar.setOnClickListener {

            var Brand = brand.text.toString().trim() { it <= ' ' }
            var Could_Be_Better = CBB.text.toString().trim() { it <= ' ' }
            var Description = description.text.toString().trim() { it <= ' ' }
            var Emission_Standard = emission_standared.text.toString().trim() { it <= ' ' }
            var Engine = engine.text.toString().trim() { it <= ' ' }
            var Engine_Type = engine_type.text.toString().trim() { it <= ' ' }
            var Fuel_Tank_Capacity = FTC.text.toString().trim() { it <= ' ' }
            var Fuel_Type = fueltype.text.toString().trim() { it <= ' ' }
            var Good_Things = good_things.text.toString().trim() { it <= ' ' }
            var Height = height.text.toString().trim() { it <= ' ' }
            //ImageUrl = ""
            var Length = length.text.toString().trim() { it <= ' ' }
            var Max_Power = maxpower.text.toString().trim() { it <= ' ' }
            var Max_Torque = maxtorque.text.toString().trim() { it <= ' ' }
            var Mileage = mileage.text.toString().trim() { it <= ' ' }
            var Model = model.text.toString().trim() { it <= ' ' }
            var Name = cname.text.toString().trim() { it <= ' ' }
            var Price = price.text.toString().trim() { it <= ' ' }
            var Seating_Capacity = seating_capacity.text.toString().trim() { it <= ' ' }
            var Transmission_Type = transmissiontype.text.toString().trim() { it <= ' ' }
            var Width = width.text.toString().trim() { it <= ' ' }
            var Ground_Clearance = ground_clearance.text.toString().trim() { it <= ' ' }
            var DriveTrain = drive_trains.text.toString().trim() { it <= ' ' }
            var Bootspace = bootspace.text.toString().trim() { it <= ' ' }
            var BodyType = body_type.text.toString().trim() { it <= ' ' }
            var Verdict = verdict.text.toString().trim() { it <= ' ' }
            var color = colors.text.toString().trim() { it <= ' ' }
            var delim = ","
            var arr = Pattern.compile(delim).split(color)
            var color1 = if (arr[0].isEmpty()) " " else arr[0]
            var color2 = if (arr[1].isEmpty()) " " else arr[1]
            var color3 = if (arr[2].isEmpty()) " " else arr[2]
            var color4 = if (arr[3].isEmpty()) " " else arr[3]
            var color5 = if (arr[4].isEmpty()) " " else arr[4]
            var just_launched = JLC.text.toString().trim() { it <= ' ' }
            var popular_car = PC.text.toString().trim() { it <= ' ' }
            var top_10_cars = T10C.text.toString().trim() { it <= ' ' }
            var upcomming_cars = UC.text.toString().trim() { it <= ' ' }

            database.child(car_id!!).child("brand").setValue(Brand)
            database.child(car_id!!).child("could_Be_Better").setValue(Could_Be_Better)
            database.child(car_id!!).child("description").setValue(Description)
            database.child(car_id!!).child("emission_Standard").setValue(Emission_Standard)
            database.child(car_id!!).child("engine").setValue(Engine)
            database.child(car_id!!).child("engine_Type").setValue(Engine_Type)
            database.child(car_id!!).child("fuel_Tank_Capacity").setValue(Fuel_Tank_Capacity)
            database.child(car_id!!).child("fuel_Type").setValue(Fuel_Type)
            database.child(car_id!!).child("good_Things").setValue(Good_Things)
            database.child(car_id!!).child("height").setValue(Height)

            database.child(car_id!!).child("length").setValue(Length)
            database.child(car_id!!).child("max_Power").setValue(Max_Power)
            database.child(car_id!!).child("max_Torque").setValue(Max_Torque)
            database.child(car_id!!).child("mileage").setValue(Mileage)
            database.child(car_id!!).child("model").setValue(Model)
            database.child(car_id!!).child("name").setValue(Name)
            database.child(car_id!!).child("price").setValue(Price)
            database.child(car_id!!).child("seating_Capacity").setValue(Seating_Capacity)
            database.child(car_id!!).child("transmission_Type").setValue(Transmission_Type)
            database.child(car_id!!).child("width").setValue(Width)
            database.child(car_id!!).child("ground_Clearance").setValue(Ground_Clearance)
            database.child(car_id!!).child("driveTrain").setValue(DriveTrain)
            database.child(car_id!!).child("bootspace").setValue(Bootspace)
            database.child(car_id!!).child("verdict").setValue(Verdict)
            database.child(car_id!!).child("color1").setValue(color1)
            database.child(car_id!!).child("color2").setValue(color2)
            database.child(car_id!!).child("color3").setValue(color3)
            database.child(car_id!!).child("color4").setValue(color4)
            database.child(car_id!!).child("color5").setValue(color5)
            database.child(car_id!!).child("just_launched").setValue(just_launched)
            database.child(car_id!!).child("popular_car").setValue(popular_car)
            database.child(car_id!!).child("top_10_cars").setValue(top_10_cars)
            database.child(car_id!!).child("upcomming_cars").setValue(upcomming_cars)
            database.child(car_id!!).child("bodyType").setValue(BodyType)
            Toast.makeText(this,"Hurry ! Update Successfully !!",Toast.LENGTH_LONG).show()
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
                    if (query.equals(i.child("name").getValue())) {

                         car_id = i.child("cid").getValue().toString()
                        var Brand = i.child("brand").getValue().toString()
                        var Could_Be_Better = i.child("could_Be_Better").getValue().toString()
                        var Description = i.child("description").getValue().toString()
                        var Emission_Standard = i.child("emission_Standard").getValue().toString()
                        var Engine = i.child("engine").getValue().toString()
                        var Engine_Type = i.child("engine_Type").getValue().toString()
                        var Fuel_Tank_Capacity = i.child("fuel_Tank_Capacity").getValue().toString()
                        var Fuel_Type = i.child("fuel_Type").getValue().toString()
                        var Good_Things = i.child("good_Things").getValue().toString()
                        var Height = i.child("height").getValue().toString()
                        var ImageUrl = i.child("imageUrl").getValue().toString()
                        var Length = i.child("length").getValue().toString()
                        var Max_Power = i.child("max_Power").getValue().toString()
                        var Max_Torque = i.child("max_Torque").getValue().toString()
                        var Mileage = i.child("mileage").getValue().toString()
                        var Model = i.child("model").getValue().toString()
                        var Name = i.child("name").getValue().toString()
                        var Price= i.child("price").getValue().toString()
                        var Seating_Capacity= i.child("seating_Capacity").getValue().toString()
                        var Transmission_Type= i.child("transmission_Type").getValue().toString()
                        var Width= i.child("width").getValue().toString()
                        var GC = i.child("ground_Clearance").getValue().toString()
                        var DriveTrain = i.child("driveTrain").getValue().toString()
                        var Bootspace = i.child("bootspace").getValue().toString()
                        var Verdict = i.child("verdict").getValue().toString()
                        var color1 = i.child("color1").getValue().toString()
                        var color2 = i.child("color2").getValue().toString()
                        var color3 = i.child("color3").getValue().toString()
                        var color4 = i.child("color4").getValue().toString()
                        var color5 = i.child("color5").getValue().toString()
                        var just_launched = i.child("just_launched").getValue().toString()
                        var popular_car = i.child("popular_car").getValue().toString()
                        var top_10_cars = i.child("top_10_cars").getValue().toString()
                        var upcomming_cars = i.child("upcomming_cars").getValue().toString()
                        var bodyType = i.child("bodyType").getValue().toString()

                        //load car image from the firebase
                        var imageref = FirebaseStorage.getInstance().reference.child("cars/" + car_id + ".jpg" )
                        imageref.downloadUrl.addOnSuccessListener { Uri ->

                            val imageURL = Uri.toString()
                            var IV = findViewById<ImageView>(R.id.car_img)
                            Glide.with(this@UpdateCar)
                                .load(ImageUrl)
                                .into(car_img)
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

    override fun onClick(v: View?) {
        showFileChooser()
    }

    private fun uploadFile()
    {
        if(filePath != null){
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading...")
            progressDialog.show()
            var imageRef : StorageReference?
            imageRef = storageReference!!.child("cars/"+car_id+".jpeg")
            var upload = imageRef.putFile(filePath!!)
                .addOnSuccessListener {
                    progressDialog.dismiss()
                    Toast.makeText(this,"File is Uploaded",Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener {
                    progressDialog.dismiss()
                    Toast.makeText(this,"Oops !! Something is wrong !",Toast.LENGTH_LONG).show()
                }
                .addOnProgressListener {taskSnapshot->
                    val progress = 100.0 * taskSnapshot.bytesTransferred/taskSnapshot.totalByteCount
                    progressDialog.setMessage("Uploaded " + progress.toInt() + "%....")
                }

            upload.addOnCompleteListener {it->
                if(it.isSuccessful){
                    imageRef.downloadUrl.addOnCompleteListener {UriTask->
                        UriTask.result?.let {
                            imageUri = it
                            database.child(car_id!!).child("imageUrl").setValue(imageUri.toString())
                            Toast.makeText(this,imageUri.toString(),Toast.LENGTH_LONG).show()
                        }

                    }
                }
            }
        }
    }

    private fun showFileChooser() {
        val intent : Intent =  Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(Intent.createChooser(intent,"SELECT PICTURE"),PIC_IMAGE_REQUEST)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == PIC_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null){
            filePath = data.data;
            try{
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,filePath)
                car_img.visibility = View.VISIBLE
                car_img.setImageBitmap(bitmap)
                uploadFile()
            }
            catch (e: IOException)
            {
                e.printStackTrace()
            }
        }
    }
}
