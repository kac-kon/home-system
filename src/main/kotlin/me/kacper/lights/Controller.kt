package me.kacper.lights

import com.pi4j.context.Context
import me.kacper.model.Addressed
import me.kacper.model.State

class Controller(
    private val context: Context
) {

    private val analog = Analog(context)
//    private val digit1 = Digital(context, Strip.Upper, Addressed(1, 40, AddressedDirection.Forward))
//    private val digit2 = Digital(context, Strip.Lower, Addressed(1, 50, AddressedDirection.Backward))
    private val leds: Map<Strip, LED> = mapOf(
        Strip.Analog to analog,
//        Strip.Upper to digit1,
//        Strip.Lower to digit2
    )

    fun color(state: State) {
        leds.values.forEach {
            it.color = state.lights[0].stripConfig.color
        }
    }



    fun ff() {
        leds.values.forEach {
            it.toggle()

            if (it.strip.stripType.type == StripTypes.Digital) {
                (it as AddressedLED).addressedConfig = it.addressedConfig.copy(
                    direction = if (it.addressedConfig.direction == AddressedDirection.Forward)
                        AddressedDirection.Backward else AddressedDirection.Forward
                )
            }
        }
    }

}