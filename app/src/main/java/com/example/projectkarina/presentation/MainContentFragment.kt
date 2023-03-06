package com.example.projectkarina.presentation

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.projectkarina.R
import com.example.projectkarina.presentation.parts.FirstPartFragment
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainContentFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        if (user == null) {
            openScreen(RegistrationFragment.newInstance())
        }

        val button = requireActivity().findViewById<Button>(R.id.buttonStartLearning)
        button.setOnClickListener {
            openScreen(FirstPartFragment.newInstance())
        }

        val callback = object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    private fun openScreen(fragment: Fragment) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment)
            .disallowAddToBackStack()
            .commit()
    }

    companion object {
        fun newInstance() = MainContentFragment()
    }
}