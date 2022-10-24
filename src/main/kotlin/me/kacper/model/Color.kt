package me.kacper.model

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import me.kacper.exceptions.ElementNotFoundException

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class Color(

    @JsonAlias(value = ["Red"])
    val red: Int,

    @JsonAlias(value = ["Green"])
    val green: Int,

    @JsonAlias(value = ["Blue"])
    val blue: Int,

    @JsonAlias(value = ["Brightness"])
    val brightness: Int
) {
    fun toMap() = mapOf(
        RED to red,
        GREEN to green,
        BLUE to blue,
        BRIGHTNESS to brightness
    )

    private fun Int.toned(): Int {
        return (this * brightness).div(100)
    }

    fun toMapToned() = mapOf(
        RED to red.toned(),
        GREEN to green.toned(),
        BLUE to blue.toned()
    )

    operator fun get(key: String): Int {
        val map = this.toMap()
        return if (key in map.keys)
            map[key]!!.toned()
        else throw ElementNotFoundException(key, map.keys)
    }

    companion object{
        const val RED = "RED"
        const val GREEN = "GREEN"
        const val BLUE = "BLUE"
        const val BRIGHTNESS = "BRIGHTNESS"
    }
}