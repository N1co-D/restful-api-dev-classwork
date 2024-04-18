package dev.restful.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Data {
    private Integer year;
    private Double price;

    @JsonProperty("CPU model")
    private String cpuModel;

    @JsonProperty("Hard disk size")
    private String hardDiskSize;
}