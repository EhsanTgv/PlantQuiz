package com.taghavi.plantquiz

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
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
            return null
        }

        override fun onPostExecute(result: List<Plant>?) {
            super.onPostExecute(result)
        }
    }
}
