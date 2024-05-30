package com.adso.myapplicationsantes
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
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
    private lateinit var autoCompleteTextViewCedula: AutoCompleteTextView
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var nextButton: Button

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
        autoCompleteTextViewCedula = findViewById(R.id.autoCompleteTextViewCedula)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        nextButton = findViewById(R.id.nextButton)

        val cedulaOptions = arrayOf("Cédula de ciudadanía", "Cédula de extranjería", "Permiso de trabajo")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, cedulaOptions)
        autoCompleteTextViewCedula.setAdapter(adapter)

        nextButton.setOnClickListener {
            val nombres = editTextNombres.text.toString()
            val apellidos = editTextApellidos.text.toString()
            val fechaNacimiento = editTextFechaNacimiento.text.toString()
            val cedula = autoCompleteTextViewCedula.text.toString()
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (nombres.isNotEmpty() && apellidos.isNotEmpty() && fechaNacimiento.isNotEmpty() && cedula.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, OtroFormulario::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    "Por favor, completa todos los campos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        editTextFechaNacimiento.setOnClickListener {
            showDatePicker()
        }
    }

    private fun showDatePicker() {
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