package me.kacper.model

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import me.kacper.lights.Strip

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class Light(

    @JsonAlias(value = ["ID"])
    val id: Strip,

    @JsonAlias(value = ["StripConfig"])
    val stripConfig: StripConfig
)
