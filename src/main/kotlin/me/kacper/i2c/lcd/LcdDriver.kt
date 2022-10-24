@file:Suppress("unused")

package me.kacper.i2c.lcd

import com.pi4j.io.i2c.I2C
import me.kacper.exceptions.LineNumberNotSupportedException

class LcdDriver(private val device: I2C) {
    init {
//        writeCmd(2)
//        writeCmd(3)
    }

    private fun lcdStrobe(data: Int) {
        try {
            device.write((data or En or LCD_BACKLIGHT).toByte())
            Thread.sleep(0, 500_000)
            device.write((data or En.inv() or LCD_BACKLIGHT).toByte())
            Thread.sleep(0, 100_000)
        } catch (e: Exception) {
            println(e.message)
        }
    }

    private fun lcdWriteFourBits(data: Int) {
        try {
            device.write((data or LCD_BACKLIGHT).toByte())
            lcdStrobe(data)
        } catch (e: Exception) {
            println(e.message)
        }
    }

    private fun lcdWrite(cmd: Int, mode: Int) {
        lcdWriteFourBits(mode or (cmd and 0xF0))
        lcdWriteFourBits(mode or (cmd.shl(4) and 0xF0))
    }

    private fun lcdWrite(cmd: Int) {
        lcdWrite(cmd, 0)
    }

    private fun writeChar(char: Int) {
        lcdWrite(char, 1)
    }

    fun displayString(str: String, line: Int) {
        when (line) {
            1 -> lcdWrite(0x80)
            2 -> lcdWrite(0xC0)
            3 -> lcdWrite(0x94)
            4 -> lcdWrite(0xD4)
            else -> throw LineNumberNotSupportedException(line)
        }
        for (i in 0 .. str.length) {
            lcdWrite(str[i].code, Rs)
        }
    }

    fun clear() {
        lcdWrite(LCD_CLEARDISPLAY)
        lcdWrite(LCD_RETURNHOME)
    }

    fun backlight(state: Boolean) {
        if (state) {
            lcdWrite(LCD_BACKLIGHT)
        } else
            lcdWrite(LCD_NOBACKLIGHT)
    }

    fun loadCustomChars(fontData: List<Array<Int>>) {
        fontData.forEach { char ->
            char.forEach { charLine ->
                writeChar(charLine)
            }
        }
    }

    fun displayStringPosition(str: String, line: Int, position: Int) {
        val pos = when (line) {
            1 -> position
            2 -> position + 0x40
            3 -> position + 0x14
            4 -> position + 0x54
            else -> throw LineNumberNotSupportedException(line)
        }
        lcdWrite(pos + 0x80)
        for (i in 0..str.length) {
            lcdWrite(str[i].code, Rs)
        }
    }

    companion object {
        // commands
        private const val LCD_CLEARDISPLAY = 0x01
        private const val LCD_RETURNHOME = 0x02
        private const val LCD_ENTRYMODESET = 0x04
        private const val LCD_DISPLAYCONTROL = 0x08
        private const val LCD_CURSORSHIFT = 0x10
        private const val LCD_FUNCTIONSET = 0x20
        private const val LCD_SETCGRAMADDR = 0x40
        private const val LCD_SETDDRAMADDR = 0x80

        // flags for display entry mode
        private const val LCD_ENTRYRIGHT = 0x00
        private const val LCD_ENTRYLEFT = 0x02
        private const val LCD_ENTRYSHIFTINCREMENT = 0x01
        private const val LCD_ENTRYSHIFTDECREMENT = 0x00

        // flags for display on/off control
        private const val LCD_DISPLAYON = 0x04
        private const val LCD_DISPLAYOFF = 0x00
        private const val LCD_CURSORON = 0x02
        private const val LCD_CURSOROFF = 0x00
        private const val LCD_BLINKON = 0x01
        private const val LCD_BLINKOFF = 0x00

        // flags for display/cursor shift
        private const val LCD_DISPLAYMOVE = 0x08
        private const val LCD_CURSORMOVE = 0x00
        private const val LCD_MOVERIGHT = 0x04
        private const val LCD_MOVELEFT = 0x00

        // flags for function set
        private const val LCD_8BITMODE = 0x10
        private const val LCD_4BITMODE = 0x00
        private const val LCD_2LINE = 0x08
        private const val LCD_1LINE = 0x00
        private const val LCD_5x10DOTS = 0x04
        private const val LCD_5x8DOTS = 0x00

        // flags for backlight control
        private const val LCD_BACKLIGHT = 0x08
        private const val LCD_NOBACKLIGHT = 0x00

        private const val En = 0b00000100  // Enable bit
        private const val Rw = 0b00000010  // Read/Write bit
        private const val Rs = 0b00000001  // Register select bit

        private val ARROW_UP: Array<Int> = arrayOf(
            0x00,
            0x04,
            0x0E,
            0x15,
            0x04,
            0x04,
            0x00,
            0x00
        )
    }

}

