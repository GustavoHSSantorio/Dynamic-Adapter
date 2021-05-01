package com.dynamicadapter.dynamic.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.dynamicadapter.dynamic.domain.model.SimpleVO
import com.dynamicadapter.dynamic.presentation.adapter.renderes.ViewRenderer

interface Dynamic {
    fun registerRenderer(renderer: ViewRenderer<*>)
    fun getRenderer(viewType: Int): ViewRenderer<RecyclerView.ViewHolder>
    fun setViewObject(vos: List<SimpleVO>, ishHidden: Boolean = false)
    fun setViewObjectDiff(vos: List<SimpleVO>, ishHidden: Boolean = false)
    fun updateViewAt(vo: SimpleVO, index: Int)
    fun notifyPositionRemovedAt(position: Int)
    fun filterByQuery(query: String, vararg type: Int)
    fun notifyChanges(vos: List<SimpleVO>)
    fun notifyItemChangeAt(position: Int, payload: Any)
    fun notifyItemChangeAt(position: Int)
    fun clear()
}
