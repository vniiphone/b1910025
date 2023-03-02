package com.travel.b1910025.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PlaceRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull
    private Long addressSecond_id;

    public Long getAddressSecond_id() {
        return addressSecond_id;
    }

    public void setAddressSecond_id(Long addressSecond_id) {
        this.addressSecond_id = addressSecond_id;
    }

    public PlaceRequest(@NotBlank(message = "Name is required") String name, @NotNull Long addressSecond_id) {
        this.name = name;
        this.addressSecond_id = addressSecond_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // @NotNull
    // private Long id;

    // @NotNull
    // private Long product_id;

}