package com.example.agenda

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {



    private  val REQUEST_GALLERY = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        abreGaleria_Click()
    }
    fun abreGaleria_Click(){
        val btnGaleria = findViewById<ImageButton>(R.id.imgfoto)

        btnGaleria.setOnClickListener(){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                    val permisoArchivos = arrayOf((Manifest.permission.READ_EXTERNAL_STORAGE))
                    requestPermissions(permisoArchivos, REQUEST_GALLERY)
                }else{
                    muestraGaleria()
                }
            }else{
                muestraGaleria()
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_GALLERY ->{
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    muestraGaleria()
                else
                    Toast.makeText(applicationContext,"No puedes acceder a tus imagenes", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun muestraGaleria() {
        val intentGaleria = Intent(Intent.ACTION_PICK)
        intentGaleria.type = "image/*"
        startActivityForResult(intentGaleria,REQUEST_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == REQUEST_GALLERY){
            val imgfoto = findViewById<ImageButton>(R.id.imgfoto)
            imgfoto.setImageURI(data?.data)
        }
    }



    @SuppressLint("ResourceType")
    fun clear(view: View) {
        var img = findViewById<ImageButton>(R.id.imgfoto)
        var nombre = findViewById<EditText>(R.id.nombre)
        var apellidoP = findViewById<EditText>(R.id.aP)
        var apellidoM = findViewById<EditText>(R.id.aM)
        var nickname = findViewById<EditText>(R.id.nickname)
        var phone = findViewById<EditText>(R.id.phone)
        var email= findViewById<EditText>(R.id.email)
        var calle= findViewById<EditText>(R.id.calle)
        var colonia= findViewById<EditText>(R.id.colonia)
        var cp= findViewById<EditText>(R.id.cp)
        img.setImageURI(findViewById(R.drawable.img_base))
        nombre.setText("")
        apellidoP.setText("")
        apellidoM.setText("")
        nickname.setText("")
        phone.setText("")
        email.setText("")
        calle.setText("")
        colonia.setText("")
        cp.setText("")
    }

    fun close(view: View) {
        finishAffinity()
    }

    fun enviar(view: View) {
        val intent = Intent(this,Second::class.java)
        var nombre = findViewById<EditText>(R.id.nombre)

        intent.putExtra("nombre", nombre.getText().toString())
        startActivity(intent)
    }
}

