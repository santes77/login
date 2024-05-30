package com.adso.myapplicationsantes
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.adso.myapplicationsantes.MainActivity.Companion.IMAGE_PICKER_CODE

class OtroFormulario : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var autoCompleteTextViewRegion: AutoCompleteTextView
    private lateinit var autoCompleteTextViewGenero: AutoCompleteTextView
    private lateinit var editTextTelefono: EditText
    private lateinit var buttonSeleccionarFoto: Button
    private lateinit var checkBoxPoliticas: CheckBox
    private lateinit var buttonRegistrarse: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otro_formulario)

        // Inicializar las vistas
        imageView = findViewById(R.id.imageView)
        autoCompleteTextViewRegion = findViewById(R.id.autoCompleteTextViewRegion)
        autoCompleteTextViewGenero = findViewById(R.id.autoCompleteTextViewGenero)
        editTextTelefono = findViewById(R.id.editTextTelefono)
        buttonSeleccionarFoto = findViewById(R.id.buttonSeleccionarFoto)
        checkBoxPoliticas = findViewById(R.id.checkBoxPoliticas)
        buttonRegistrarse = findViewById(R.id.buttonRegistrarse)

        // Configurar el AutoCompleteTextView para la región
        val regionAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            getRegiones()
        )
        autoCompleteTextViewRegion.setAdapter(regionAdapter)

        // Configurar el AutoCompleteTextView para el género
        val generoAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            getGeneros()
        )
        autoCompleteTextViewGenero.setAdapter(generoAdapter)

        // Configurar el botón para seleccionar la foto de perfil
        buttonSeleccionarFoto.setOnClickListener {

            buttonSeleccionarFoto.setOnClickListener {
                // Agrega aquí la lógica para seleccionar una imagen de perfil
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, IMAGE_PICKER_CODE)
            }

            // Aquí iría el código para abrir el selector de archivos y establecer la foto de perfil
        }

        // Configurar el botón de registro
        buttonRegistrarse.setOnClickListener {
            if (checkBoxPoliticas.isChecked) {
                // Aquí iría el código para registrar al usuario
            } else {
                // Mostrar un mensaje indicando que debe aceptar las políticas de privacidad
            }
        }
    }

    private fun getRegiones(): Array<String> {
        // Aquí iría el código para obtener la lista de regiones disponibles
        return arrayOf("Región 1", "Región 2", "Región 3")
    }

    private fun getGeneros(): Array<String> {
        // Aquí iría el código para obtener la lista de géneros disponibles
        return arrayOf("Masculino", "Femenino", "Otro")
    }
}