package me.kacper.i2c.lcd

object CustomChars {
    private val ARROW_UP: List<Int> = listOf(
        0x00,
        0x04,
        0x0E,
        0x15,
        0x04,
        0x04,
        0x00,
        0x00
    )
    private val ARROW_UP_RIGHT: List<Int> = listOf(
        0x00,
        0x0F,
        0x03,
        0x05,
        0x09,
        0x10,
        0x00,
        0x00
    )
    private val ARROW_RIGHT: List<Int> = listOf(
        0x04,
        0x02,
        0x1F,
        0x02,
        0x04,
        0x00,
        0x00
    )
    private val ARROW_DOWN_RIGHT: List<Int> = listOf(
        0x00,
        0x10,
        0x09,
        0x05,
        0x03,
        0x0F,
        0x00,
        0x00
    )
    private val ARROW_DOWN: List<Int> = listOf(
        0x00,
        0x04,
        0x04,
        0x15,
        0x0E,
        0x04,
        0x00,
        0x00
    )
    private val ARROW_DOWN_LEFT: List<Int> = listOf(
        0x00,
        0x01,
        0x12,
        0x14,
        0x18,
        0x1E,
        0x00,
        0x00
    )
    private val ARROW_LEFT: List<Int> = listOf(
        0x00,
        0x04,
        0x08,
        0x1F,
        0x08,
        0x04,
        0x00,
        0x00
    )
    private val ARROW_UP_LEFT: List<Int> = listOf(
        0x00,
        0x1E,
        0x18,
        0x14,
        0x12,
        0x01,
        0x00,
        0x00
    )
    val arrows: Set<List<Int>> = setOf(
        ARROW_UP,
        ARROW_UP_RIGHT,
        ARROW_RIGHT,
        ARROW_DOWN_RIGHT,
        ARROW_DOWN,
        ARROW_DOWN_LEFT,
        ARROW_LEFT,
        ARROW_UP_LEFT
    )
}