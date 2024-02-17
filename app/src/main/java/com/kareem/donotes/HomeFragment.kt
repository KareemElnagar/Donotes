package com.kareem.donotes

import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import com.kareem.donotes.adapter.NotesAdapter
import com.kareem.donotes.database.NotesDatabase
import com.kareem.donotes.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val LOG_TAG: String = HomeFragment::class.java.simpleName
    override val LayoutInflater: (LayoutInflater) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    override fun setup() {
        binding.apply {
            FAB.setOnClickListener {
                replaceFragment(CreateNoteFragment(), true)
            }
            lifecycleScope.launch {

                context?.let {
                    val notes = NotesDatabase.getDaoInstance(
                        NotesApplication.getApplicationContext()
                    ).getAllNotes()

                    recyclerView.adapter = NotesAdapter(notes)
                }


            }


        }

    }


}