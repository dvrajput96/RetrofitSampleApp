package com.example.sampleapp.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelSetup()
        viewSetup()
    }

    abstract fun viewModelSetup()

    abstract fun viewSetup()
}
