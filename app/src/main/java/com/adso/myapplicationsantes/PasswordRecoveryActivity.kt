package com.adso.myapplicationsantes

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class PasswordRecoveryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_recovery)
        FirebaseApp.initializeApp(this)

        // Obtener referencias a los elementos de la interfaz
        val emailEditText = findViewById<EditText>(R.id.editTextEmail)
        val enviarbutoon = findViewById<Button>(R.id.enviarbutton)

        // Inicializar la instancia de FirebaseAuth
        val firebaseAuth = FirebaseAuth.getInstance()

        // Configurar el click listener del botón de envío
        enviarbutoon.setOnClickListener {
            recoverPassword(emailEditText, firebaseAuth)
        }
    }

    private fun recoverPassword(emailEditText: EditText, firebaseAuth: FirebaseAuth) {
        val email = emailEditText.text.toString().trim()

        // Validar que el campo de email no esté vacío
        if (email.isEmpty()) {
            emailEditText.error = "Ingresa tu correo electrónico"
            return
        }

        // Enviar un correo de recuperación de contraseña usando FirebaseAuth
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                // Mostrar un mensaje de éxito al usuario
                Toast.makeText(this, "Se ha enviado un correo de recuperación de contraseña", Toast.LENGTH_SHORT).show()

                // Regresar a la actividad de inicio de sesión
                finish()
            }
            .addOnFailureListener { exception ->
                // Mostrar un mensaje de error al usuario
                Toast.makeText(this, "Error al enviar el correo de recuperación de contraseña: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }
}