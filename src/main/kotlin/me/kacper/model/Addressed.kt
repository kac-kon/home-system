package me.kacper.model

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import me.kacper.lights.AddressedDirection

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class Addressed(

    @JsonAlias(value = ["Frequency"])
    val frequency: Int,

    @JsonAlias(value = ["Count"])
    val count: Int,

    @JsonAlias(value = ["Direction"])
    val direction: AddressedDirection
)
