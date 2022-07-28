package me.kacper.gpio

enum class Pins(val pin: Int) {
    RED(17),
    GREEN(22),
    BLUE(27),
    ADDRESSED(18),
    IR_RX(14),
    IR_TX(15),
    I2C_SDA(2),
    I2C_SCL(3),

    SPI_CS(8),
    SPI_MISO(9),
    SPI_MOSI(10),
    SPI_SCLK(11),

    HC_SR04_TRIG(23),
    HC_SR04_ECHO(24),
    PHOTORESISTOR(25),
    HC_SR501(7)
}