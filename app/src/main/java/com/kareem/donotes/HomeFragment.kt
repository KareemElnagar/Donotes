package com.kareem.donotes

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.kareem.donotes.adapter.NotesAdapter
import com.kareem.donotes.database.NotesDatabase
import com.kareem.donotes.databinding.FragmentHomeBinding
import com.kareem.donotes.entities.Notes
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    var arrNotes = ArrayList<Notes>()
    var notesAdapter: NotesAdapter = NotesAdapter()
    override val LOG_TAG: String = HomeFragment::class.java.simpleName
    override val LayoutInflater: (LayoutInflater) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate




    private val onClicked = object : NotesAdapter.OnItemClickListener {
        override fun onClick(notesId: Int) {
            var fragment : Fragment
            var bundle = Bundle()
            bundle.putString("edit","isEdit")
            bundle.putInt("notesId",notesId)
            fragment = CreateNoteFragment()
            fragment.arguments = bundle
            replaceFragment(fragment, true)

        }
    }

    override fun setup() {
        arguments?.let {

        }

        binding.apply {
            FAB.setOnClickListener {
                replaceFragment(CreateNoteFragment.newInstance(), true)
            }
            lifecycleScope.launch {

                context?.let {
                    val notes = NotesDatabase.getDaoInstance(
                        NotesApplication.getApplicationContext()
                    ).getAllNotes()

                    notesAdapter!!.setData(notes)
                    recyclerView.adapter = notesAdapter
                }


            }

            notesAdapter!!.setOnClickListener(onClicked)
        }

    }


}