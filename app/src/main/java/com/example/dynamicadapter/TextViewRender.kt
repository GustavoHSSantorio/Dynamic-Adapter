package com.example.dynamicadapter

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dynamicadapter.dynamic.domain.mapper.convertToVO
import com.dynamicadapter.dynamic.domain.model.SimpleVO
import com.dynamicadapter.dynamic.presentation.adapter.renderes.ViewRenderer

class TextViewRender : ViewRenderer<TextViewRender.TextViewHolder>("DynamicTextView", 1) {

    inner class TextViewHolder(val textView : TextView) : RecyclerView.ViewHolder(textView)

    override fun bindView(model: SimpleVO, holder: TextViewHolder, position: Int) {
        val textVO = model.value.convertToVO<TextVO>()
        holder.textView.text = textVO.text
    }

    override fun createViewHolder(parent: ViewGroup): TextViewHolder =
        TextViewHolder(TextView(parent.context))
}