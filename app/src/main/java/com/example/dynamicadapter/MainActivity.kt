package com.example.dynamicadapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dynamicadapter.dynamic.presentation.adapter.Dynamic
import com.dynamicadapter.dynamic.presentation.adapter.DynamicAdapter
import com.example.dynamicadapter.databinding.LayoutBinding

class MainActivity : AppCompatActivity() {

    private val dynamicAdapter : Dynamic = DynamicAdapter()

    lateinit var binding : LayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutBinding.inflate(layoutInflater)
        binding.recycler.adapter =  dynamicAdapter as DynamicAdapter
    }

}