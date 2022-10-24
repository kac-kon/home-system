package me.kacper.service.controllers

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import com.pi4j.context.impl.DefaultContextBuilder
import me.kacper.lights.Controller
import me.kacper.model.State

@RestController
@RequestMapping(value = ["light"])
class LightManagementController {

    private val context = DefaultContextBuilder.newInstance().build()
    private val controller = Controller(context)

    @RequestMapping(
        value = ["/asd"],
        method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getState(
        @RequestParam(value = "name") name: String
    ): String {
        return """{"name": "$name"}"""
    }

    @PostMapping(
        value = ["/toggle"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun toggle(): String {
        controller.ff()
        return """{"time toggled": "${System.currentTimeMillis()}"}"""
    }

    @PostMapping(
        value = ["/color"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun color(
            @RequestBody body: State
    ) {
        controller
    }
}