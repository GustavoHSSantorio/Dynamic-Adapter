package com.example.dynamicadapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dynamicadapter.dynamic.domain.model.SimpleVO
import com.dynamicadapter.dynamic.presentation.adapter.Dynamic
import com.dynamicadapter.dynamic.presentation.adapter.DynamicAdapter
import com.example.dynamicadapter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private val dynamicAdapter: Dynamic = DynamicAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter =  dynamicAdapter as DynamicAdapter
        dynamicAdapter.registerRenderer(TextViewRender())
        dynamicAdapter.setViewObjectDiff(
            listOf(
                SimpleVO(
                    key = "DynamicTextView",
                    value = TextVO("textaaaaa")
                ),
                SimpleVO(
                    key = "DynamicTextView",
                    value = TextVO("textaaaaa")
                )
            )
        )

    }

}