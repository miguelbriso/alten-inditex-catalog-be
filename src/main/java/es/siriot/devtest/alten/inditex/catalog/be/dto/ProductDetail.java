package es.siriot.devtest.alten.inditex.catalog.be.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDetail {

    @NotNull(message = "The 'id' cannot be null.")
    @Size(min = 1, message = "The 'id' must not be less than 1 character.")
    private String id;

    @NotNull(message = "The 'name' cannot be null.")
    @Size(min = 1, message = "The 'name' must not be less than 1 character.")
    private String name;

    @NotNull(message = "The 'price' cannot be null.")
    private Float price;

    @NotNull(message = "The 'availability' cannot be null.")
    private Boolean availability;

}
