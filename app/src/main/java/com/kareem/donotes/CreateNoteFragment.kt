package com.kareem.donotes

import android.os.Build
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.kareem.donotes.database.NotesDatabase
import com.kareem.donotes.databinding.FragmentCreateNoteBinding
import com.kareem.donotes.entities.Notes
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class CreateNoteFragment : BaseFragment<FragmentCreateNoteBinding>() {
    private var currentDate: String? = null
    override val LOG_TAG: String = CreateNoteFragment::class.java.simpleName
    override val LayoutInflater: (LayoutInflater) -> FragmentCreateNoteBinding =
        FragmentCreateNoteBinding::inflate

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setup() {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val currentDate = LocalDateTime.now().format(formatter)
        binding.apply {
            buttonDone.setOnClickListener {
                saveNote()
            }
            buttonBack.setOnClickListener {
                replaceFragment(HomeFragment(), true)
            }
            TVdateTime.text = currentDate
        }

    }

    private fun saveNote() {
        binding.apply {

            if (noteTitle.text.isNullOrEmpty()) {
                Toast.makeText(context, "Note Title is Required", Toast.LENGTH_SHORT).show()

            } else if (noteText.text.isNullOrEmpty()) {

                Toast.makeText(context, "Note Description is Required", Toast.LENGTH_SHORT).show()
            } else {

                lifecycleScope.launch {
                    val notes = Notes()
                    notes.title = noteTitle.text.toString()
                    notes.noteText = noteText.text.toString()
                    notes.dateTime = currentDate
                    context?.let {
                        NotesDatabase.getDaoInstance(it).insertNotes(notes)
                        noteTitle.setText("")
                        noteText.setText("")
                        requireActivity().supportFragmentManager.popBackStack()
                    }
                }
            }
        }


    }


}