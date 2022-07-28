package me.kacper.lights

enum class Strip(val stripType: StripType) {
    Upper(StripType.Addressed),
    Lower(StripType.Addressed),
    Analog(StripType.Analog)
}

enum class StripType {
    Addressed,
    Analog
}

enum class AddressedDirection(val code: Int) {
    Forward(1),
    Backward(-1)
}