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

    private var mGoogleSignInClient: GoogleSignInClient? = null
    private lateinit var googleSignInButton: Button
    private lateinit var accederButton: Button
    private lateinit var registrateButton: Button

    companion object {
        private const val RC_SIGN_IN = 9001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ingreso)

        // Configurar opciones de inicio de sesión con Google
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        // Obtener referencia al botón de inicio de sesión con Google
        googleSignInButton = findViewById<Button>(R.id.googleSignInButton)
        googleSignInButton.setOnClickListener(View.OnClickListener { signInWithGoogle() })

        // Obtener referencia al botón de acceso
        accederButton = findViewById<Button>(R.id.loginButton)
        accederButton.setOnClickListener {
            navigateToHome()
        }

        // Obtener referencia al botón de registrarse
        registrateButton = findViewById<Button>(R.id.registrateButton)
        registrateButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun navigateToMain() {

    }

    private fun signInWithGoogle() {
        val signInIntent = mGoogleSignInClient?.signInIntent
        if (signInIntent != null) {
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
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

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}