package com.dynamicadapter.dynamic.presentation.adapter

import android.util.SparseArray
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.dynamicadapter.dynamic.domain.model.SimpleVO
import com.dynamicadapter.dynamic.presentation.adapter.renderes.EmptyViewHolder
import com.dynamicadapter.dynamic.presentation.adapter.renderes.ViewRenderer
import javax.inject.Inject


class DynamicAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    Dynamic {

    private val mDiffer: AsyncListDiffer<SimpleVO?> = AsyncListDiffer(this, SimpleVOItemCallback)

    var ishHidden: Boolean = false
    var renderers = SparseArray<ViewRenderer<RecyclerView.ViewHolder>>()
    private val originalList: ArrayList<SimpleVO> = arrayListOf()
    var bindingLiveData: MutableLiveData<Int>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return renderers.get(viewType)?.createViewHolder(parent)
            ?: EmptyViewHolder(FrameLayout(parent.context))
    }

    override fun getItemCount(): Int = mDiffer.currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bindingLiveData?.value = position
        mDiffer.currentList.getOrNull(position)?.let { vo ->
            findRenderOrNull(vo.key)?.bindView(vo, holder, ishHidden, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return mDiffer.currentList.getOrNull(position)?.let {
            findRenderOrNull(it.key)?.viewType ?: super.getItemViewType(position)
        } ?: super.getItemViewType(position)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun registerRenderer(renderer: ViewRenderer<*>) {
        if (renderers.get(renderer.viewType) == null) {
            renderers.put(renderer.viewType, renderer as ViewRenderer<RecyclerView.ViewHolder>)
        }
    }

    override fun getRenderer(viewType: Int): ViewRenderer<RecyclerView.ViewHolder> =
        renderers.get(viewType)

    override fun setViewObject(vos: List<SimpleVO>, ishHidden: Boolean) {
        this.ishHidden = ishHidden
        this.mDiffer.submitList(vos)
        originalList.addAll(vos)
    }

    override fun setViewObjectDiff(vos: List<SimpleVO>, ishHidden: Boolean) {
        this.ishHidden = ishHidden
        mDiffer.submitList(vos)
    }

    override fun updateViewAt(vo: SimpleVO, index: Int) {
        val list = mDiffer.currentList.toMutableList()
        list[index] = vo
        setViewObjectDiff(list.toList().toList() as List<SimpleVO>, ishHidden)
    }

    override fun notifyPositionRemovedAt(position: Int) {
        val list = mDiffer.currentList.toMutableList()
        list.removeAt(position)
        setViewObjectDiff(list.toList().toList() as List<SimpleVO>, ishHidden)
    }

    override fun filterByQuery(query: String, vararg type: Int) {
        val filteredVos = mutableListOf<SimpleVO>()

        originalList.forEach { vo ->
            findRenderOrNull(vo.key)?.let { render ->
                if (type.contains(render.viewType) && render.filterByQuery(query, vo)) {
                    filteredVos.add(vo)
                } else {
                    filteredVos.add(vo)
                }
            }
        }

        setViewObjectDiff(filteredVos)
    }

    override fun notifyChanges(vos: List<SimpleVO>) {
        with(mDiffer) {
            submitList(vos)
        }
        notifyDataSetChanged()
    }

    override fun notifyItemChangeAt(position: Int, payload: Any) {
        notifyItemChanged(position, payload)
    }

    override fun notifyItemChangeAt(position: Int) {
        notifyItemChanged(position)
    }

    private inline fun iterateOnRenders(action: (ViewRenderer<RecyclerView.ViewHolder>) -> Unit) {
        for (i in 0 until renderers.size()) {
            val key = renderers.keyAt(i)
            action(renderers.get(key))
        }
    }

    private fun findRenderOrNull(key: String): ViewRenderer<RecyclerView.ViewHolder>? {
        iterateOnRenders {
            if (it.key == key) return it
        }
        return null
    }

    override fun clear() {
        mDiffer.submitList(emptyList())
    }
}
