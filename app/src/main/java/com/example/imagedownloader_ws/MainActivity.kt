package com.example.imagedownloader_ws

import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.net.URL

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var bkgdThread: Thread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //we can use this for multiple buttons but here we only demo single button
        findViewById<Button>(R.id.fetchBtn)?.setOnClickListener(this)
    }

    override fun onClick(v: View?){
        when (v?.id){
            R.id.fetchBtn -> {
                //get url from edittext
                val url = findViewById<EditText>(R.id.url_input).text.toString()
                //create file obj for saving image, save image as image.jpg
                val file = makeFile("image.jpg")
                downloadToFile(url, file)
            }
        }
    }

    //stream to stream copy. using bkgtread
    fun downloadToFile(url: String, file: File){
        bkgdThread = Thread{
            try {
                URL(url).openStream().use { input ->
                    file.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }
                runOnUiThread {
                    updateImageView(file)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        bkgdThread?.start()
    }

    //create file obj in directory_pic, a pre defined folder for images.
    //created file obj contains both directory and filename
    fun makeFile(fname: String): File{
        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File(dir, fname)
    }

    fun updateImageView(file: File){
        val bitmap = BitmapFactory.decodeFile(file.absolutePath) //passed to bitmapfactory to decode into a bitmap
        val imageView = findViewById<ImageView>(R.id.imageView) //display bitmap in imageview
        imageView.setImageBitmap(bitmap)
    }
}