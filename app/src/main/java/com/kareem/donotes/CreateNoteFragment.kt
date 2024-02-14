package com.kareem.donotes

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.kareem.donotes.database.NotesDatabase
import com.kareem.donotes.databinding.FragmentCreateNoteBinding
import com.kareem.donotes.entities.Notes
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date


class CreateNoteFragment : BaseFragment<FragmentCreateNoteBinding>() {
    var currentDate: String? = null
    override val LOG_TAG: String = CreateNoteFragment::class.java.simpleName
    override val LayoutInflater: (LayoutInflater) -> FragmentCreateNoteBinding =
        FragmentCreateNoteBinding::inflate

    override fun setup() {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        currentDate = sdf.format(Date())
        binding.apply {
            TVdateTime.text = "date"
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
            } else if (noteSubtitle.text.isNullOrEmpty()) {

                Toast.makeText(context, "Note Sub Title is Required", Toast.LENGTH_SHORT).show()
            } else if (noteText.text.isNullOrEmpty()) {

                Toast.makeText(context, "Note Description is Required", Toast.LENGTH_SHORT).show()
            } else {

                lifecycleScope.launch {
                    val notes = Notes()
                    notes.title = noteTitle.text.toString()
                    notes.subTitle = noteSubtitle.text.toString()
                    notes.noteText = noteText.text.toString()
                    notes.dateTime = currentDate
                    context?.let {
                        NotesDatabase.getDatabase(it).noteDao().insertNotes(notes)
                        noteTitle.setText("")
                        noteSubtitle.setText("")
                        noteText.setText("")
                        requireActivity().supportFragmentManager.popBackStack()
                    }
                }
            }
        }


    }


}