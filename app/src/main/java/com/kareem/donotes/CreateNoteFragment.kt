package com.kareem.donotes

import BaseFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kareem.donotes.databinding.FragmentCreateNoteBinding


class CreateNoteFragment : BaseFragment<FragmentCreateNoteBinding>() {
    override val LOG_TAG: String = CreateNoteFragment::class.java.simpleName
    override val LayoutInflater: (LayoutInflater) -> FragmentCreateNoteBinding = FragmentCreateNoteBinding::inflate
    override fun setup() {

    }


}