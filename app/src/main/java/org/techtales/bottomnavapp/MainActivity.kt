package org.techtales.bottomnavapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import org.techtales.bottomnavapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Assign fragment to Variable
        val homeFragment = HomeFragment()
        val personFragment = PersonFragment()
        val settingsFragment = SettingsFragment()

        setCurrentFragment(homeFragment)
        //set navigation item selected listener for get the navigation translation
        binding.bottomBar.setOnNavigationItemSelectedListener {

            //used 'when' to get id by condition
            when(it.itemId){
                R.id.home ->setCurrentFragment(homeFragment)
                R.id.person ->setCurrentFragment(personFragment)
                R.id.setting ->setCurrentFragment(settingsFragment)
            }
            true
        }
    }

    //create a method for set fragment and simplify the code for reassign
    private fun setCurrentFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentLayout, fragment)
            commit()
        }

    }
}