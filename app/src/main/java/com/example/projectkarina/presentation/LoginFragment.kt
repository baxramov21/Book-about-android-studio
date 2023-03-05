package com.example.projectkarina.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.projectkarina.R
import com.example.projectkarina.databinding.FragmentLoginBinding
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
                if (email.isNotEmpty() && password.isNotEmpty())
                    loginAccount(email, password)
                else
                    showToast(getString(R.string.fill_fields))
            }
        }
        binding.tvSignup.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, RegistrationFragment.newInstance())
                .disallowAddToBackStack()
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
                    Log.d(TAG, "signInWithEmail:success")
                    showToast(getString(R.string.login_success))
                    openMainScreen()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    showToast(getString(R.string.auth_failed))
                }
            }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun openMainScreen() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, MainContentFragment.newInstance())
            .disallowAddToBackStack()
            .commit()

    }

    companion object {
        fun newInstance() = LoginFragment()
        private const val TAG = "LOGIN"
    }
}