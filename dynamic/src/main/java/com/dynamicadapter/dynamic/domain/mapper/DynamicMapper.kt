package com.dynamicadapter.dynamic.domain.mapper

import com.fasterxml.jackson.databind.ObjectMapper

inline fun <reified T : Any> Any?.convertToVO(): T =
    ObjectMapper().convertValue(this, T::class.java) as T

inline fun <reified T : Any> ArrayList<*>?.convertToArrayVO(): ArrayList<T> {
    val newArray = arrayListOf<T>()
    this?.forEach {
        newArray.add(ObjectMapper().convertValue(it, T::class.java))
    }
    return newArray
}

fun Any.convertToVO(clazz: Class<*>): Any = ObjectMapper().convertValue(this, clazz)
