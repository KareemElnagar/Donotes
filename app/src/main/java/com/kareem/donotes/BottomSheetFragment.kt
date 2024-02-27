package com.kareem.donotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kareem.donotes.databinding.FragmentNotesBottomSheetBinding

class BottomSheetFragment : BottomSheetDialogFragment() {
    lateinit var binding: FragmentNotesBottomSheetBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notes_bottom_sheet,container,false)
    }
    private fun setListener(){
        binding.bottomSheetAddPhoto.setOnClickListener {

        }
    }
}