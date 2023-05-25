package com.example.intentsapp

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    lateinit var btnSmsI:Button
    lateinit var btnEmailI:Button
    lateinit var btnCameraI:Button
    lateinit var btnShareI:Button
    lateinit var btnPesaI:Button
    lateinit var btnCallI:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSmsI = findViewById(R.id.btnsms)
        btnEmailI = findViewById(R.id.btnemail)
        btnCameraI = findViewById(R.id.btncamera)
        btnShareI = findViewById(R.id.btnshare)
        btnPesaI = findViewById(R.id.btnMpesa)
        btnCallI = findViewById(R.id.btncall)

        btnSmsI.setOnClickListener {
            val uri = Uri.parse("smsto:0799194320")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "The SMS text")
            startActivity(intent)

        }
        btnEmailI.setOnClickListener {
            val emailIntent =
                Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "jeremykipkirui254@gmail.com@gmail.com", null))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Greetings")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello...")
            startActivity(Intent.createChooser(emailIntent, "Send email..."))

        }
        btnCameraI.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureIntent, 1)

        }
        btnShareI.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey, download this app!")
            startActivity(shareIntent)

        }
        btnPesaI.setOnClickListener {
            val simToolKitLaunchIntent =
                applicationContext.packageManager.getLaunchIntentForPackage("com.android.stk")
            simToolKitLaunchIntent?.let { startActivity(it) }

        }
        btnCallI.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0799194320"))
            if (ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    android.Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf<String>(android.Manifest.permission.CALL_PHONE),
                    1
                )
            } else {
                startActivity(intent)
            }
           
        }
    }
}