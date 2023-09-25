package org.techtales.bottomnavapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import org.techtales.bottomnavapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    //Assign fragment to Variable
    val homeFragment = HomeFragment()
    val personFragment = PersonFragment()
    val settingsFragment = SettingsFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        actionBarDrawerToggle = ActionBarDrawerToggle(this,binding.drawerlayout, R.string.nav_open, R.string.nav_close)

        binding.drawerlayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        setCurrentFragment(homeFragment)

        binding.navview.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home ->{
                    setCurrentFragment(homeFragment)
                    //close drawer when we click new item
                    binding.drawerlayout.closeDrawers()
                }
                R.id.person ->{
                    setCurrentFragment(personFragment)
                    binding.drawerlayout.closeDrawers()
                }
                R.id.setting ->{
                    setCurrentFragment(settingsFragment)
                    binding.drawerlayout.closeDrawers()
                }
            }
            true
        }

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

    // drawer open close
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            true
        }
        else super.onOptionsItemSelected(item)
    }

    //create a method for set fragment and simplify the code for reassign
    private fun setCurrentFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentLayout, fragment)
            commit()
        }

    }
}