package com.example.asynctaskexample

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


class DownloadAsyncTask(context : Context, imageView : ImageView?) : AsyncTask<String?,Int?, Bitmap?>(){
    private var context : Context? = null
    private var imageView : ImageView? = null
    private var progressDialog : ProgressDialog? =null
    init {
        this.context = context
        this.imageView = imageView
    }

    override fun onPreExecute() {
        super.onPreExecute()
        progressDialog = ProgressDialog(context)
        progressDialog?.setMessage("Downloading please wait")
        progressDialog?.show()
    }


    override fun doInBackground(vararg params: String?): Bitmap? {
        var bitmapImage : Bitmap? = null
        try {
            var imageUrl = URL(params[0])
            val conn: HttpURLConnection = imageUrl.openConnection() as HttpURLConnection
            conn.setDoInput(true)
            conn.connect()
            val options = BitmapFactory.Options()
            options.inPreferredConfig = Bitmap.Config.ARGB_8888
            bitmapImage = BitmapFactory.decodeStream(conn?.inputStream, null, options)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bitmapImage
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        progressDialog?.dismiss()
        if(result != null){
            imageView?.setImageBitmap(result)
        }
    }
}