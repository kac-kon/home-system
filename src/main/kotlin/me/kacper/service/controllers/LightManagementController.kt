package me.kacper.service.controllers

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["light"])
class LightManagementController {

    @RequestMapping(
        value = ["/asd"],
        method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getState(
        @RequestParam(value = "name") name: String
    ): String {
        return """{"name": "$name"}"""
    }
}