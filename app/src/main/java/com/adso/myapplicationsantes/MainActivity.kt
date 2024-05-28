package com.adso.myapplicationsantes

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var editTextNombres: EditText
    private lateinit var editTextApellidos: EditText
    private lateinit var editTextFechaNacimiento: EditText
    private lateinit var editTextCedula: EditText
    private lateinit var selectImageButton: Button
    private lateinit var privacyPolicyCheckbox: CheckBox
    private lateinit var registerButton: Button

    companion object {
        internal const val IMAGE_PICKER_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        textView = findViewById(R.id.textView)
        editTextNombres = findViewById(R.id.editTextText)
        editTextApellidos = findViewById(R.id.editTextText2)
        editTextFechaNacimiento = findViewById(R.id.editTextText3)
        editTextCedula = findViewById(R.id.editTextCedula)
        selectImageButton = findViewById(R.id.selectImageButton)
        privacyPolicyCheckbox = findViewById(R.id.privacyPolicyCheckbox)
        registerButton = findViewById(R.id.registerButton)

        selectImageButton.setOnClickListener {
            // Agrega aquí la lógica para seleccionar una imagen de perfil
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, IMAGE_PICKER_CODE)
        }

        registerButton.setOnClickListener {
            val nombres = editTextNombres.text.toString()
            val apellidos = editTextApellidos.text.toString()
            val fechaNacimiento = editTextFechaNacimiento.text.toString()
            val cedula = editTextCedula.text.toString()

            if (nombres.isNotEmpty() && apellidos.isNotEmpty() && fechaNacimiento.isNotEmpty() && cedula.isNotEmpty() && privacyPolicyCheckbox.isChecked) {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, Ingreso::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    "Por favor, completa todos los campos y acepta la política de privacidad",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        editTextFechaNacimiento.setOnClickListener {
            showDatePicker()
        }
    }

    private fun showDatePicker() {
        // Agrega aquí la lógica para mostrar un selector de fecha
        val calendar = Calendar.getInstance()

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { datePicker: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(Calendar.YEAR, year)
                selectedDate.set(Calendar.MONTH, month)
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)

                editTextFechaNacimiento.setText(formattedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }
}