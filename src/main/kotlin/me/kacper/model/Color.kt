package me.kacper.model

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

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
        BLUE to blue
    )

    private fun Int.tone(): Int {
        return (this * brightness).div(100)
    }

    fun toMapToned() = mapOf(
        RED to red.tone(),
        GREEN to green.tone(),
        BLUE to blue.tone()
    )

    companion object{
        const val RED = "RED"
        const val GREEN = "GREEN"
        const val BLUE = "BLUE"
    }
}