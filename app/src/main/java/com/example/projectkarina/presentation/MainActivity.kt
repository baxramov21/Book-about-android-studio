package com.example.projectkarina.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.projectkarina.R
import com.example.projectkarina.databinding.ActivityMainBinding
import com.example.projectkarina.presentation.parts.*
import com.firebase.ui.auth.AuthUI
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var mAuthUI: AuthUI
    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        auth = Firebase.auth
        mAuthUI = AuthUI.getInstance()

        if (auth.currentUser == null) {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }

        createDrawer()
        openScreen(MainContentFragment.newInstance())
    }

    private fun openScreen(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment)
            .disallowAddToBackStack()
            .commit()
    }

    private fun createDrawer() {
        val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nvd_menu)
        toggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            listener(it.itemId)
            drawer.closeDrawers()
            true
        }
    }

    private fun listener(itemId: Int) {
        when (itemId) {
            R.id.part1 -> openScreen(FirstPartFragment.newInstance())
            R.id.part2 -> openScreen(SecondPartFragment.newInstance())
            R.id.part3 -> openScreen(ThirdPartFragment.newInstance())
            R.id.part4 -> openScreen(FourthPartFragment.newInstance())
            R.id.part5 -> openScreen(FifthPartFragment.newInstance())
            R.id.part6 -> openScreen(SixthPartFragment.newInstance())
            R.id.part7 -> openScreen(SeventhPartFragment.newInstance())
            R.id.part8 -> openScreen(EighthPartFragment.newInstance())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.sign_out -> {
                signOut()
            }
        }

        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
        openScreen(RegistrationFragment.newInstance())
    }
}