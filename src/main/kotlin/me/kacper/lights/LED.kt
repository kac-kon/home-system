package me.kacper.lights

import me.kacper.model.Addressed
import me.kacper.model.Color

interface LED {

    val turnedOn: Boolean

    var color: Color

    val strip: Strip

    val type: StripTypes
        get() = strip.stripType.type

    fun turnOn()

    fun turnOff()

    fun toggle() {
        if (turnedOn) {
            turnOff()
        } else
            turnOn()
    }
}

interface AddressedLED : LED {
    var addressedConfig: Addressed
}