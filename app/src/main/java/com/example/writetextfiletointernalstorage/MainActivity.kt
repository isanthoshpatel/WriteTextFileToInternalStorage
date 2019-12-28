package com.example.writetextfiletointernalstorage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    var fos: FileOutputStream? = null
    var fis: FileInputStream? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_save.setOnClickListener {
            saveText()
        }
        bt_load.setOnClickListener {
            loadText()
        }

    }//onCreate

    fun saveText() {
        try {
            fos = openFileOutput("File1.txt", Context.MODE_PRIVATE)
            fos?.write(et_text.text.toString().toByteArray())
            et_text.text.clear()
            Toast.makeText(
                this,
                "File Saved Successfully" + "\n" + filesDir + "/" + "File1.txt",
                Toast.LENGTH_LONG
            ).show()

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (fos != null) {
                try {
                    fos?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }


    }

    fun loadText() {
        try {
            fis = openFileInput("File1.txt")
            var isr = InputStreamReader(fis)
            var br = BufferedReader(isr)
            if (br != null) {
                var sb = StringBuilder()
                sb.append(br.readLine()).append("\n")
                et_text.setText(sb.toString())
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (fis != null) {
                try {
                    fis?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }


}//MainActivity
