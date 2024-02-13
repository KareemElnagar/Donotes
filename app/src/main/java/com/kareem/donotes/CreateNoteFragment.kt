package com.kareem.donotes

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.kareem.donotes.database.NotesDatabase
import com.kareem.donotes.databinding.FragmentCreateNoteBinding
import com.kareem.donotes.entities.Notes
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date


class CreateNoteFragment : BaseFragment<FragmentCreateNoteBinding>() {
    override val LOG_TAG: String = CreateNoteFragment::class.java.simpleName
    override val LayoutInflater: (LayoutInflater) -> FragmentCreateNoteBinding =
        FragmentCreateNoteBinding::inflate

    override fun setup() {
        binding.apply {
            TVdateTime.text = "date"
            buttonDone.setOnClickListener {
                //  saveNote()
            }
            buttonBack.setOnClickListener {
                replaceFragment(HomeFragment(), true)
            }
        }

    }


}