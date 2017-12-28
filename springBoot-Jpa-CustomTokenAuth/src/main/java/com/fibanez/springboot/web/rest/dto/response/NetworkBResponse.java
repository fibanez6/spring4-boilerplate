package com.fibanez.springboot.web.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NetworkBResponse implements Serializable {

    @JsonProperty("coords")
    private int[] coords;

    @JsonProperty("patches")
    private int patches;

}
