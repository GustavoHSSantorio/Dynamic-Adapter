package com.dynamicadapter.dynamic.domain.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class SimpleVO(
    @JsonProperty("key") val key: String = "",
    @JsonProperty("title") val title: String? = "",
    @JsonProperty("value") val value: Any? = null
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SimpleVO

        if (key != other.key) return false
        if (title != other.title) return false
        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = key.hashCode()
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (value?.hashCode() ?: 0)
        return result
    }
}
