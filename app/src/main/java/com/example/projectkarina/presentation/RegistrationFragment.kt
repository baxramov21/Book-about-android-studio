package com.example.projectkarina.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.Fragment
import com.example.projectkarina.R
import com.example.projectkarina.databinding.FragmentRegistrationBinding
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistrationFragment : Fragment() {


    private lateinit var auth: FirebaseAuth

    private var _binding: FragmentRegistrationBinding? = null
    private val binding: FragmentRegistrationBinding
        get() = _binding ?: throw RuntimeException("Binding is null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideActionBar()

        auth = Firebase.auth

        val user = auth.currentUser
        if (user != null) {
            openMainScreen()
        } else {
            var email: String
            var password: String

            with(binding) {
                buttonSignUp.setOnClickListener {
                    email = userEmail.text.toString()
                    password = userPasswrod.text.toString()
                    if (email.isNotEmpty() && password.isNotEmpty())
                        createAccount(email, password)
                    else
                        showToast(getString(R.string.fill_fields))
                }
            }
        }

        binding.tvLogin.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.auth_container, LoginFragment.newInstance())
                .addToBackStack("Registration")
                .commit()
        }

        val callback = object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
                requireActivity().finishAffinity()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }



    private fun openMainScreen() {
        val intent = Intent(context,MainActivity::class.java)
        startActivity(intent)
    }

    private fun hideActionBar() {
        requireActivity().actionBar?.hide()
    }


    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    showToast(getString(R.string.signup_succes))
                    val user = auth.currentUser
                    openMainScreen()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    showToast(getString(R.string.auth_failed))
                }
            }
    }

    companion object {
        fun newInstance() = RegistrationFragment()
        private const val TAG = "SIGNUP"
    }
}
