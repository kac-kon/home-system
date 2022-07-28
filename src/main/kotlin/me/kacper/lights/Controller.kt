package me.kacper.lights

import com.pi4j.context.Context

class Controller(
    private val context: Context
) {

    private val analog = Analog(context)
    private val leds: Map<Strip, LED> = mapOf(
        Strip.Analog to analog
    )

    private fun ff() {
        leds.values.forEach {
            it.toggle()

            if (it.strip.stripType == StripType.Addressed) {
                (it as AddressedLED).addressed.direction.code
            }
        }
    }

}