package com.example.projectkarina.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.projectkarina.R
import com.example.projectkarina.databinding.FragmentLoginBinding
import com.example.projectkarina.presentation.MainActivity
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding ?: throw RuntimeException("Binding cannot be empty")

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideActionBar()

        auth = FirebaseAuth.getInstance()

        var email: String
        var password: String

        with(binding) {
            buttonSignUp.setOnClickListener {
                email = userEmail.text.toString()
                password = userPasswrod.text.toString()
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    progressBar.visibility = ProgressBar.VISIBLE
                    loginAccount(email, password)
                } else
                    showToast(getString(R.string.fill_fields))
            }
        }
        binding.tvSignup.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.auth_container, RegistrationFragment.newInstance())
                .commit()

        }
    }

    private fun hideActionBar() {
        requireActivity().actionBar?.hide()
    }

    private fun loginAccount(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    makeProgressBarInvisible(binding.progressBar)
                    showToast(getString(R.string.login_success))
                    openMainScreen()
                } else {
                    makeProgressBarInvisible(binding.progressBar)
                    showToast(getString(R.string.auth_failed))
                }
            }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun openMainScreen() {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
    }

    private fun makeProgressBarInvisible(progressBar: ProgressBar) {
        progressBar.visibility = ProgressBar.INVISIBLE
    }


    companion object {
        fun newInstance() = LoginFragment()
        private const val TAG = "LOGIN"
    }
}