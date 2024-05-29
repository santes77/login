package com.adso.myapplicationsantes

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class Ingreso : AppCompatActivity() {

    companion object {
        private const val RC_SIGN_IN = 9001
    }

    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ingreso)

        // Configurar opciones de inicio de sesión con Google
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        // Obtener referencia al botón de inicio de sesión con Google
        val googleSignInButton: Button = findViewById(R.id.googleSignInButton)
        googleSignInButton.setOnClickListener(View.OnClickListener { signInWithGoogle() })

        // Obtener referencia al botón de acceder
        val accederButton: Button = findViewById(R.id.accederButton)
        accederButton.setOnClickListener {
            val mensajeBienvenida = "¡Bienvenido a Ayuemplen! Donde las oportunidades te están esperando."
            Toast.makeText(this, mensajeBienvenida, Toast.LENGTH_SHORT).show()
            navigateToHome()
        }

        // Obtener referencia al botón de registrarse
        val registrateButton: Button = findViewById(R.id.registrateButton)
        registrateButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Obtener referencia al botón "Olvidaste tu contraseña?"
        val OlvideContra: Button = findViewById(R.id.forgotPasswordButton)
        OlvideContra.setOnClickListener {
            val intent = Intent(this@Ingreso, PasswordRecoveryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun navigateToHome() {
        val intent = Intent(this, HOME::class.java)
        startActivity(intent)
    }

    private fun signInWithGoogle() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Resultado devuelto por la actividad de inicio de sesión de Google
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Inicio de sesión exitoso con Google
                val account = task.getResult(ApiException::class.java)
                showSuccessfulRegistrationMessage()
                navigateToHome()
            } catch (e: ApiException) {
                // Error en el inicio de sesión con Google
                Toast.makeText(this, "Error al iniciar sesión con Google", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showSuccessfulRegistrationMessage() {
        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
    }
}