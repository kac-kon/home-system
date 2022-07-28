package me.kacper.gpio

@Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
class Int8 {
    companion object {
        fun Int.int8(): Int{
            if (this < 0)
                return 0
            else if (this > 255)
                return 255
            return this
        }
    }
}