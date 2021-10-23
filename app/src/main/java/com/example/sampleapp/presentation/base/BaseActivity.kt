package com.example.sampleapp.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelSetup()
        viewSetup()
    }

    abstract fun viewModelSetup()

    abstract fun viewSetup()

}