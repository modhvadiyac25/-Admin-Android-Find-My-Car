package com.example.admin_findmycar

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_get_and_set_image.*
import java.io.IOException

class GetAndSetImage : AppCompatActivity() , View.OnClickListener {


    lateinit var imageUri : Uri
    private var filePath : Uri? =   null
    val PIC_IMAGE_REQUEST = 1234
    internal var storage : FirebaseStorage? = null
    internal var storageReference : StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_and_set_image)

        //to hide action bar in login
        val actionBar: ActionBar? = supportActionBar
        actionBar!!.hide()

        storageReference = FirebaseStorage.getInstance()!!.reference

        btnchoose.setOnClickListener(this)
        btnupload.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v === btnchoose){
            showFileChooser()
        }else if(v === btnupload){
            uploadFile()
        }
    }

    private fun uploadFile() {
        if(filePath != null){
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading...")
            progressDialog.show()

            val imageRef = storageReference!!.child("cars/"+"file1.jpeg")
            var upload = imageRef.putFile(filePath!!)
                .addOnSuccessListener {task->
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
                img.setImageBitmap(bitmap)
            }catch (e:IOException){
                e.printStackTrace()
            }
        }
    }
}
