package com.kareem.donotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.kareem.donotes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val homeFragment = HomeFragment()
    private val createNoteFragment = CreateNoteFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        replaceFragment(homeFragment,true)

    }

    private fun replaceFragment(fragment:  Fragment, isTransition: Boolean) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        if (isTransition){
            fragmentTransition.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
        }
        fragmentTransition.replace(R.id.frame_layout, fragment)
            .addToBackStack(fragment.javaClass.simpleName).commit()

    }
}