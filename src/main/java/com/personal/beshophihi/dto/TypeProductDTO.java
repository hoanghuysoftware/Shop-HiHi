package com.personal.beshophihi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TypeProductDTO {
    @JsonProperty("type-name")
    @Size(min = 5, max = 20, message = "Type name of product must be between 5 to 20 lengths !")
    private String nameType;
}
