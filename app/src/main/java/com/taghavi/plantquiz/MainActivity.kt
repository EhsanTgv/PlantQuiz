package com.taghavi.plantquiz

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.AsyncTask
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private val openCameraButtonRequestId = 1234
    private val openPhotoGalleryButtonRequestId = 1235

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        openCameraButton.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, openCameraButtonRequestId)
        }

        openPhotoGalleryButton.setOnClickListener {
            val galleryIntent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(galleryIntent, openPhotoGalleryButtonRequestId)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == openCameraButtonRequestId) {
            if (resultCode == Activity.RESULT_OK) {
                val imageData = data!!.extras!!.get("data") as Bitmap
                imageView.setImageBitmap(imageData)
            }
        }

        if (requestCode == openPhotoGalleryButtonRequestId) {
            if (resultCode == Activity.RESULT_OK) {
                val contentURI = data!!.data
                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                imageView.setImageBitmap(bitmap)
            }
        }
    }

    fun buttonOneIsClicked(view: View) {

    }

    fun buttonTwoIsClicked(view: View) {

    }

    fun buttonThreeIsClicked(view: View) {

    }

    fun buttonFourIsClicked(view: View) {

    }

    inner class DownloadingPlantTask : AsyncTask<String, Int, List<Plant>>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): List<Plant>? {
            val downloadingObject = DownloadingObject()
            val jsonData =
                downloadingObject.downloadJSONDataFrom("http://plantplaces.com/perl/mobile/flashcard.pl")
            Log.i("JsonData", jsonData)

            return null
        }

        override fun onPostExecute(result: List<Plant>?) {
            super.onPostExecute(result)
        }
    }
}
