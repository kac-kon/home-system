package me.kacper.lights

import com.pi4j.context.Context
import com.pi4j.io.pwm.Pwm
import com.pi4j.io.pwm.PwmConfig
import com.pi4j.io.pwm.PwmType
import me.kacper.gpio.Pins
import me.kacper.model.Color

class Analog(
    private val context: Context,
    color: Color = Color(255, 127, 127, 100),
    override val turnedOn: Boolean = false
) : LED {
    override val strip: Strip = Strip.Analog
    override var color: Color = color
        set(value) {
            setColors(value)
            field = value
        }

    private val channels: Map<String, Pwm> =
        setOf(Pins.RED, Pins.BLUE, Pins.GREEN)
            .associate { it.name to generateConfig(it.pin) }

    override fun turnOn() {
        channels.values.forEach { it.on(color.brightness) }
    }

    override fun turnOff() {
        channels.values.forEach { it.off() }
    }

    private fun setColors(color: Color){
        color.toMapToned().forEach {
            channels[it.key]?.on(it.value)
        }
    }

    private fun generateConfig(pin: Int): Pwm {
        val pwmConfig: PwmConfig = Pwm.newConfigBuilder(context)
            .id("BCM_$pin")
            .pwmType(PwmType.SOFTWARE)
            .address(pin)
            .provider("pigpio-pwm")
            .initial(color.brightness)
            .shutdown(0)
            .frequency(10_000)
            .build()
        return context.create(pwmConfig)
    }
}