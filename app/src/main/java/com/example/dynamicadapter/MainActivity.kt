package com.example.dynamicadapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dynamicadapter.dynamic.presentation.adapter.Dynamic
import com.dynamicadapter.dynamic.presentation.adapter.DynamicAdapter

class MainActivity : AppCompatActivity() {

    private val dynamicAdapter : Dynamic = DynamicAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}