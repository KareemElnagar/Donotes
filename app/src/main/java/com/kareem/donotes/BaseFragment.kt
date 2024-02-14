package com.kareem.donotes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    abstract val LOG_TAG: String?
    abstract val LayoutInflater: (LayoutInflater) -> VB
    private var _binding: ViewBinding? = null

    protected val binding
        get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LayoutInflater(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    abstract fun setup()
    protected fun log(msg: Any) {
        Log.v(LOG_TAG, msg.toString())
    }
    fun replaceFragment(fragment:  Fragment, isTransition: Boolean) {
        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()
        if (isTransition){
            fragmentTransition.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
        }
        fragmentTransition.replace(R.id.frame_layout, fragment)
            .addToBackStack(fragment.javaClass.simpleName).commit()

    }

}