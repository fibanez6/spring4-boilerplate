package com.fibanez.springboot.web.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NetworkAResponse implements Serializable {

    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    @JsonProperty("account_id")
    private long accountId;

    @JsonProperty("country")
    private String country;

    @JsonProperty("ad_format")
    private String adFormat;

    @JsonProperty("app_id")
    private Long appId;

    @JsonProperty("revenue")
    private Long revenue;

    @JsonProperty("impressions")
    private Integer impressions;

    @JsonProperty("clicks")
    private Integer clicks;

}
