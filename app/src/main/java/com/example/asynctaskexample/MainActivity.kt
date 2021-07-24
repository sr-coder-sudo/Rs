package com.example.asynctaskexample

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Base64
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.asynctaskexample.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding?.downloadButton?.setOnClickListener {
            var downloadTask =
                DownloadAsyncTask(this,binding?.imageView)
            downloadTask?.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "https://www.tutorialspoint.com/images/tp-logo-diamond.png")
        }

    }


}