package me.kacper.lights

enum class Strip(val stripType: StripType) {
    Upper(DigitalStrip()),
    Lower(DigitalStrip()),
    Analog(AnalogStrip())
}

enum class StripID(val label: String) {
    Upper("upper"),
    Lower("lower"),
    Analog("analog")
}

enum class StripTypes(val label: String) {
    Digital("digital"),
    Analog("analog")
}

enum class AddressedDirection(val code: Int) {
    Forward(1),
    Backward(-1)
}

interface StripType {
    val type: StripTypes
}

private class DigitalStrip : StripType {
    override val type: StripTypes = StripTypes.Digital
}

private class AnalogStrip : StripType {
    override val type: StripTypes = StripTypes.Analog
}