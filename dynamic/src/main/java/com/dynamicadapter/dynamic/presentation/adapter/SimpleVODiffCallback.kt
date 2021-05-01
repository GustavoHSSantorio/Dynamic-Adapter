package com.dynamicadapter.dynamic.presentation.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.dynamicadapter.dynamic.domain.model.SimpleVO
import com.dynamicadapter.dynamic.domain.DynamicComparable
import java.util.*

object SimpleVOItemCallback : DiffUtil.ItemCallback<SimpleVO>(){
    override fun areItemsTheSame(oldItem: SimpleVO, newItem: SimpleVO): Boolean {
        return try {
            oldItem.key == newItem.key
        } catch (e: Exception) {
            return false
        }
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: SimpleVO, newItem: SimpleVO): Boolean {

        if (!oldItem.title.equals(newItem.title, true))
            return false

        val oldItemValue = oldItem.value
        val newItemValue = newItem.value

        return when {
            oldItemValue is AbstractMap<*, *> && newItemValue is AbstractMap<*, *> -> oldItemValue == newItemValue
            oldItemValue is DynamicComparable -> oldItemValue.contentEquals(newItemValue)
            else -> oldItemValue == newItemValue
        }
    }
}