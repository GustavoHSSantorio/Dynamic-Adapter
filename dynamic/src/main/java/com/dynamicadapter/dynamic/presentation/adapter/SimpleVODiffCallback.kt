package com.dynamicadapter.dynamic.presentation.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.dynamicadapter.dynamic.domain.model.SimpleVO
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

        val oldItemValue = oldItem.value
        val newItemValue = newItem.value

        return when {
            //Note: Because retrofit return AbstracMap
            oldItemValue is AbstractMap<*, *> && newItemValue is AbstractMap<*, *> -> oldItemValue == newItemValue
            else -> oldItemValue == newItemValue
        }
    }
}