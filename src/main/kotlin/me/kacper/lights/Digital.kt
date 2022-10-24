package me.kacper.lights

import com.diozero.ws281xj.PixelColour
import com.diozero.ws281xj.rpiws281x.WS281x
import com.pi4j.context.Context
import com.diozero.ws281xj.sampleapps.WS281xTest
import me.kacper.gpio.Pins
import me.kacper.model.Addressed
import me.kacper.model.Color
import me.kacper.model.Color.Companion.BLUE
import me.kacper.model.Color.Companion.GREEN
import me.kacper.model.Color.Companion.RED

class Digital(
    private val context: Context,
    override val strip: Strip,
    addressedConfig: Addressed,
    color: Color = Color(255, 255, 255, 100),
    override val turnedOn: Boolean = false
) : AddressedLED {
    private val initConfig = addressedConfig
    override var color: Color = color
        set(value) {
            setColors(value)
            field = value
        }

    override var addressedConfig: Addressed = initConfig
        set(value) {
            field = value
            setColors(color)
        }

    val test = WS281xTest()

    private val stripe = WS281x(Pins.ADDRESSED.pin, 255, Constants.Addressed.COUNT)

    override fun turnOn() {
        setColors(color)
    }

    override fun turnOff() {
        stripe.allOff()
    }

    private fun setColors(color: Color) {
        val setColor = PixelColour.createColourRGB(color[RED], color[GREEN], color[BLUE])
        val range = if (addressedConfig.direction == AddressedDirection.Forward)
            0 until initConfig.count else initConfig.count -1 downTo 0
        for (i in range step addressedConfig.direction.code) {
            if (i < initConfig.count && i % addressedConfig.frequency == 0)
                stripe.setPixelColour(i, setColor)
            else
                stripe.setPixelColour(i, zeroColor)
            stripe.render()
        }
    }

    fun setColors(index: Int, color: Color) {
        val setColor = PixelColour.createColourRGB(color[RED], color[GREEN], color[BLUE])
        stripe.setPixelColour(index, setColor)
    }

    private companion object {
        val zeroColor = PixelColour.createColourRGB(0, 0, 0)
    }
}