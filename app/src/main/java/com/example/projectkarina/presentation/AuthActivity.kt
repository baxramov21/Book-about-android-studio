package com.example.projectkarina.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectkarina.R

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        supportActionBar?.hide()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.auth_container, RegistrationFragment.newInstance())
            .commit()
    }
}