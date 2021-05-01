package com.dynamicadapter.dynamic.presentation.adapter.renderes

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dynamicadapter.dynamic.domain.model.SimpleVO

abstract class ViewRenderer<VH : RecyclerView.ViewHolder>(val key: String, val viewType: Int) {

    abstract fun bindView(model: SimpleVO, holder: VH, position: Int)
    abstract fun createViewHolder(parent: ViewGroup): VH
    open fun filterByQuery(query: String, simpleVO: SimpleVO) = false
}
