package com.taghavi.plantquiz.controllers

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.taghavi.plantquiz.models.DownloadingObject
import com.taghavi.plantquiz.models.Plant
import com.taghavi.plantquiz.R
import com.taghavi.plantquiz.models.ParsePlantUtility

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private val openCameraButtonRequestId = 1234
    private val openPhotoGalleryButtonRequestId = 1235

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (checkInternetConnection()) {
            val downloadingPlantTask = DownloadingPlantTask()
            downloadingPlantTask.execute()
        }

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

    override fun onResume() {
        super.onResume()
        checkInternetConnection()
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

    private fun checkInternetConnection(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        val isDeviceConnectedToInternet = networkInfo != null && networkInfo.isConnectedOrConnecting
        return if (isDeviceConnectedToInternet) {
            true
        } else {
            internetAlertDialog()
            false
        }
    }

    private fun internetAlertDialog() {
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle("Network Error")
        alertDialog.setMessage("Please check for internet connection")

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK") { dialog, wich ->
            startActivity(Intent(Settings.ACTION_SETTINGS))
        }

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel") { dialog, wich ->
            Toast.makeText(this, "You must be connected to the internet", Toast.LENGTH_SHORT).show()
            finish()
        }

        alertDialog.show()
    }

    inner class DownloadingPlantTask : AsyncTask<String, Int, List<Plant>>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): List<Plant>? {
//            val downloadingObject = DownloadingObject()
//            val jsonData =
//                downloadingObject.downloadJSONDataFrom("http://plantplaces.com/perl/mobile/flashcard.pl")
//            Log.i("JsonData", jsonData)
            val parsePlant = ParsePlantUtility()

            return parsePlant.parsePlantObjectsFromJsonData()
        }

        override fun onPostExecute(result: List<Plant>?) {
            super.onPostExecute(result)

            val numberOfPlants = result?.size ?: 0

            if (numberOfPlants > 0) {
                var randomPlantIndexForButton1: Int = (Math.random() * result!!.size).toInt()
                var randomPlantIndexForButton2: Int = (Math.random() * result.size).toInt()
                var randomPlantIndexForButton3: Int = (Math.random() * result.size).toInt()
                var randomPlantIndexForButton4: Int = (Math.random() * result.size).toInt()
            }
        }
    }
}
