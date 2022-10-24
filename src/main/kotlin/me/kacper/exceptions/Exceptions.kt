package me.kacper.exceptions

class ElementNotFoundException(
    private val elementKey: Any?, private val collection: Any?
) : Throwable("Element $elementKey not found in collection of $collection")

class LineNumberNotSupportedException(
    private val line: Int
) : Throwable("Line $line is not supported. Supported lines: 1..4")