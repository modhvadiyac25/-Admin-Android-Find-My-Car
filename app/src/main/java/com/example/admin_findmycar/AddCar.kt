package com.example.admin_findmycar

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.admin_findmycar.Models.Car
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_add_car.*
import kotlinx.android.synthetic.main.activity_add_car.btnchoose
import kotlinx.android.synthetic.main.activity_get_and_set_image.*
import kotlinx.android.synthetic.main.activity_upload_pic.*
import java.io.IOException
import java.util.regex.Pattern

class AddCar : AppCompatActivity() , View.OnClickListener  {

    //for image
    lateinit var imageUri : Uri
    private var filePath : Uri? =   null
    val PIC_IMAGE_REQUEST = 1234
    internal var storage : FirebaseStorage? = null
    internal var storageReference : StorageReference? = FirebaseStorage.getInstance().reference

    //for add car normal data
    var cid: String = ""
    var Brand: String = ""
    var Could_Be_Better: String = ""
    var Description: String = ""
    var Emission_Standard: String = ""
    var Engine: String = ""
    var Engine_Type: String = ""
    var Fuel_Tank_Capacity: String = ""
    var Fuel_Type: String = ""
    var Good_Things: String = ""
    var Height: String = ""
    var ImageUrl: String = ""
    var Length: String = ""
    var Max_Power: String = ""
    var Max_Torque: String = ""
    var Mileage: String = ""
    var Model: String = ""
    var Name: String = ""
    var Price: String = ""
    var Seating_Capacity: String = ""
    var Transmission_Type: String = ""
    var Width: String = ""
    var Ground_Clearance: String = ""
    var DriveTrain: String = ""
    var Bootspace: String = ""
    var BodyType: String = ""
    var Verdict: String = ""
    var color1: String = ""
    var color2: String = ""
    var color3: String = ""
    var color4: String = ""
    var color5: String = ""
    var just_launched: String = ""
    var popular_car: String = ""
    var top_10_cars: String = ""
    var upcomming_cars: String = ""

    var database = FirebaseDatabase.getInstance().getReference().child("cars")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_car)

        //to hide action bar in login
        val actionBar: ActionBar? = supportActionBar
        actionBar!!.hide()

        //for image
        btnchoose.setOnClickListener(this)

        //for add car normal data
        btn_addCar.setOnClickListener {
            when {
                TextUtils.isEmpty(brand.text.toString().trim() { it <= ' ' }) -> {
                    Toast.makeText(
                        this,
                        "Please Enter First Name",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {

                    Brand = brand.text.toString().trim() { it <= ' ' }
                    Could_Be_Better = CBB.text.toString().trim() { it <= ' ' }
                    Description = description.text.toString().trim() { it <= ' ' }
                    Emission_Standard = emission_standared.text.toString().trim() { it <= ' ' }
                    Engine = engine.text.toString().trim() { it <= ' ' }
                    Engine_Type = engine_type.text.toString().trim() { it <= ' ' }
                    Fuel_Tank_Capacity = FTC.text.toString().trim() { it <= ' ' }
                    Fuel_Type = fueltype.text.toString().trim() { it <= ' ' }
                    Good_Things = good_things.text.toString().trim() { it <= ' ' }
                    Height = height.text.toString().trim() { it <= ' ' }
                    //ImageUrl = ""
                    Length = length.text.toString().trim() { it <= ' ' }
                    Max_Power = maxpower.text.toString().trim() { it <= ' ' }
                    Max_Torque = maxtorque.text.toString().trim() { it <= ' ' }
                    Mileage = mileage.text.toString().trim() { it <= ' ' }
                    Model = model.text.toString().trim() { it <= ' ' }
                    Name = cname.text.toString().trim() { it <= ' ' }
                    Price = price.text.toString().trim() { it <= ' ' }
                    Seating_Capacity = seating_capacity.text.toString().trim() { it <= ' ' }
                    Transmission_Type = transmissiontype.text.toString().trim() { it <= ' ' }
                    Width = width.text.toString().trim() { it <= ' ' }
                    Ground_Clearance = ground_clearance.text.toString().trim() { it <= ' ' }
                    DriveTrain = drive_trains.text.toString().trim() { it <= ' ' }
                    Bootspace = bootspace.text.toString().trim() { it <= ' ' }
                    BodyType = body_type.text.toString().trim() { it <= ' ' }
                    Verdict = verdict.text.toString().trim() { it <= ' ' }
                    var color = colors.text.toString().trim() { it <= ' ' }
                    var delim = ","
                    var arr = Pattern.compile(delim).split(color)
                    color1 = if (arr[0].isEmpty()) " " else arr[0]
                    color2 = if (arr[1].isEmpty()) " " else arr[1]
                    color3 = if (arr[2].isEmpty()) " " else arr[2]
                    color4 = if (arr[3].isEmpty()) " " else arr[3]
                    color5 = if (arr[4].isEmpty()) " " else arr[4]
                    just_launched = JLC.text.toString().trim() { it <= ' ' }
                    popular_car = PC.text.toString().trim() { it <= ' ' }
                    top_10_cars = T10C.text.toString().trim() { it <= ' ' }
                    upcomming_cars = UC.text.toString().trim() { it <= ' ' }
//                    cid = database.push().key.toString()
                    //upload img after cid is initialize bcoz cid is file name
//                    uploadFile()
                    database.child(cid)
                        .setValue(
                            Car(
                                cid,Brand, Could_Be_Better, Description, Emission_Standard, Engine, Engine_Type, Fuel_Tank_Capacity, Fuel_Type, Good_Things, Height, ImageUrl, Length, Max_Power, Max_Torque,
                                Mileage,
                                Model,
                                Name,
                                Price,
                                Seating_Capacity,
                                Transmission_Type,
                                Width,
                                Ground_Clearance,
                                DriveTrain,
                                Bootspace,
                                BodyType,
                                Verdict,
                                color1,
                                color2,
                                color3,
                                color4,
                                color5,
                                just_launched,
                                popular_car,
                                top_10_cars,
                                upcomming_cars
                            )
                        )
                    Toast.makeText(
                        applicationContext,
                        "Hurry !! Car Add ",
                        Toast.LENGTH_LONG
                    ).show()

                    brand.setText(null)
                    CBB.setText(null)
                    description.setText(null)
                    emission_standared.setText(null)
                    engine.setText(null)
                    engine_type.setText(null)
                    FTC.setText(null)
                    fueltype.setText(null)
                    good_things.setText(null)
                    height.setText(null)
                    length.setText(null)
                    maxpower.setText(null)
                    maxtorque.setText(null)
                    mileage.setText(null)
                    model.setText(null)
                    cname.setText(null)
                    price.setText(null)
                    seating_capacity.setText(null)
                    transmissiontype.setText(null)
                    width.setText(null)
                    ground_clearance.setText(null)
                    drive_trains.setText(null)
                    bootspace.setText(null)
                    body_type.setText(null)
                    verdict.setText(null)
                    colors.setText(null)
                    JLC.setText(null)
                    PC.setText(null)
                    T10C.setText(null)
                    UC.setText(null)

                }
            }
        }
    }

    override fun onClick(v: View?) {
        showFileChooser()
    }

    private fun uploadFile() {
        if(filePath != null){
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading...")
            progressDialog.show()
            var imageRef :StorageReference?
            cid = database.push().key.toString()
     imageRef = storageReference!!.child("cars/"+cid+".jpeg")
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
                            ImageUrl = imageUri.toString()
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
            }catch (e: IOException){
                e.printStackTrace()
            }
        }
    }
}