package com.example.admin_findmycar

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_upload_pic.*

class UploadPic : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_pic)

        imageView.setOnClickListener {
            checkPermission()
        }
    }

    val READIMAGE:Int =253
    fun checkPermission(){

        if(Build.VERSION.SDK_INT >= 23){
            if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),READIMAGE)
                return
            }
        }
        loadImage()
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            READIMAGE->{
                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    loadImage()
                }else{
                    Toast.makeText(this,"Access Denied " , Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    val PIC_IMAGE_CODE = 123
    fun loadImage(){
        startActivityForResult(
            Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI),PIC_IMAGE_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PIC_IMAGE_CODE && resultCode == Activity.RESULT_OK && data != null){
             val selectedImage =  data?.data
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = contentResolver.query(selectedImage!!,filePathColumn,null,null,null)
            cursor?.moveToFirst()
            val ColumnIndex = cursor?.getColumnIndex(filePathColumn[0])
            val picturePath = cursor?.getString(ColumnIndex!!)
            cursor?.close()
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath))
        }
    }
}
