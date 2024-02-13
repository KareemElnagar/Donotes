package com.kareem.donotes

import android.view.LayoutInflater
import com.kareem.donotes.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val LOG_TAG: String = HomeFragment::class.java.simpleName
    override val LayoutInflater: (LayoutInflater) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    override fun setup() {
        binding.apply {
            FAB.setOnClickListener {
                replaceFragment(CreateNoteFragment(), true)
            }

        }

    }


}