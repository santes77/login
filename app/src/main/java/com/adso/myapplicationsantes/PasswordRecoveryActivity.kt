package com.adso.myapplicationsantes
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adso.myapplicationsantes.R
import com.adso.myapplicationsantes.R.id.editTextEmail
import com.google.firebase.auth.FirebaseAuth


class PasswordRecoveryActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var sendButton: Button
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_recovery)

        // Obtener referencias a los elementos de la interfaz
        emailEditText = findViewById(editTextEmail)
        sendButton = findViewById(R.id.senbutton)

        // Inicializar la instancia de FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        // Configurar el click listener del botón de envío
        sendButton.setOnClickListener {
            recoverPassword()
        }
    }

    private fun recoverPassword() {
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
