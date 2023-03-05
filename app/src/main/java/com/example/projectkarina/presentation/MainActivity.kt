package com.example.projectkarina.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.projectkarina.R
import com.example.projectkarina.databinding.ActivityMainBinding
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var mAuthUI: AuthUI
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        auth = Firebase.auth
        mAuthUI = AuthUI.getInstance()

        val user = auth.currentUser
        if (user != null) {
            openScreen(MainContentFragment.newInstance())
        } else {
            openScreen(RegistrationFragment.newInstance())
        }
    }

    private fun openScreen(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment)
            .disallowAddToBackStack()
            .commit()
    }


    companion object {
        private const val TAG = "MAIN"
    }

}