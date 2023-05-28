package com.example.projectkarina.presentation

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.projectkarina.NotesListFragment
import com.example.projectkarina.R
import com.example.projectkarina.databinding.ActivityMainBinding
import com.example.projectkarina.presentation.login.AuthActivity
import com.example.projectkarina.presentation.login.RegistrationFragment
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

    private fun createDrawer() {
        val drawer: DrawerLayout = findViewById(com.example.projectkarina.R.id.drawer_layout)
        val navView: NavigationView = findViewById(com.example.projectkarina.R.id.nvd_menu)
        toggle = ActionBarDrawerToggle(
            this,
            drawer,
            R.string.open,
            R.string.close
        )
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
            R.id.notes -> openScreenByAddingToBackStack(NotesListFragment.newInstance())
        }
    }

    private fun createList(): HashMap<String, Fragment> {
        val myHashMap = HashMap<String, Fragment>()
        myHashMap[getString(R.string.first_part_data)] = FirstPartFragment.newInstance()
        myHashMap[getString(R.string.second_part_data1)] = SecondPartFragment.newInstance()
        myHashMap[getString(R.string.second_part_data2)] = SecondPartFragment.newInstance()
        myHashMap[getString(R.string.third_part_data1)] = ThirdPartFragment.newInstance()
        myHashMap[getString(R.string.third_part_data2)] = ThirdPartFragment.newInstance()
        myHashMap[getString(R.string.fourth_part_data1)] = FourthPartFragment.newInstance()
        myHashMap[getString(R.string.fourth_part_data2)] = FourthPartFragment.newInstance()
        myHashMap[getString(R.string.fifth_part_data)] = FifthPartFragment.newInstance()
        myHashMap[getString(R.string.sixth_part_data1)] = SixthPartFragment.newInstance()
        myHashMap[getString(R.string.seventh_part_data1)] = SeventhPartFragment.newInstance()
        myHashMap[getString(R.string.seventh_part_data2)] = SeventhPartFragment.newInstance()
        myHashMap[getString(R.string.seventh_part_data3)] = SeventhPartFragment.newInstance()
        myHashMap[getString(R.string.eighth_part_data)] = EighthPartFragment.newInstance()
        return myHashMap
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)

        val searchItem = menu.findItem(R.id.search_bar)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView

        createSearchView(searchView)
        return super.onCreateOptionsMenu(menu)
    }

    private fun createSearchView(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            val myList = createList()

            override fun onQueryTextSubmit(query: String): Boolean {
                val fragment = findQuery(myList, query)
                if (fragment != null) {
                    openScreen(fragment)
                    searchView.onActionViewCollapsed()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.not_found),
                        Toast.LENGTH_LONG
                    ).show()

                    openScreen(MainContentFragment.newInstance())
                    searchView.onActionViewCollapsed()
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
    }


    private fun findQuery(map: HashMap<String, Fragment>, query: String): Fragment? {
        val keys = map.keys
        var fragment: Fragment? = null
        keys.map {
            if (it.contains(query)) {
                fragment = map[it]
            }
        }

        return fragment
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

    private fun openScreen(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }

    private fun openScreenByAddingToBackStack(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.main_container, fragment)
            .commit()
    }

    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
        openScreen(RegistrationFragment.newInstance())
    }
}