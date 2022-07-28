package me.kacper.lights

import com.diozero.ws281xj.PixelColour
import com.diozero.ws281xj.rpiws281x.WS281x
import com.pi4j.context.Context
import com.diozero.ws281xj.sampleapps.WS281xTest
import me.kacper.gpio.Pins
import me.kacper.model.Addressed
import me.kacper.model.Color

class Digital(
    private val context: Context,
    override val strip: Strip,
    val addressedConfig: Addressed = defaultConfig,
    color: Color = Color(255, 255, 255, 100),
    override val turnedOn: Boolean = false
) : LED {
    override var color: Color = color
        set(value) {
            setColors(value)
            field = value
        }

    val test = WS281xTest()

    val stripe = WS281x(Pins.ADDRESSED.pin, color.brightness, Constants.Addressed.COUNT)
//    stripe

    override fun turnOn() {
        TODO("Not yet implemented")
    }

    override fun turnOff() {
        TODO("Not yet implemented")
    }

    private fun setColors(color: Color){
        val cl = PixelColour.createColourRGB(color.red, color.green, color.blue)
        for (i in 0 .. addressedConfig.count) {
            stripe.setPixelColour(i, cl)
        }
    }

    private companion object {
        val defaultConfig = Addressed(
            Constants.Addressed.FREQUENCY,
            Constants.Addressed.COUNT,
            Constants.Addressed.DIRECTION
        )
    }
}