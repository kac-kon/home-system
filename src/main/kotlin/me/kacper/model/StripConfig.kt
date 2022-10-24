package me.kacper.model

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import me.kacper.lights.StripTypes

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class StripConfig(

    @JsonAlias(value = ["Color"])
    val color: Color,

    @JsonAlias(value = ["Type"])
    val type: StripTypes,

    @JsonAlias(value = ["Enabled"])
    val enabled: Boolean,

    @JsonAlias(value = ["Addressed"])
    val addressed: Addressed?
)
