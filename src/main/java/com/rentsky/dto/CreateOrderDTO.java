package com.rentsky.dto;

import com.rentsky.entity.SkiStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class CreateOrderDTO {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String phoneNumber;
    @Min(value=1)
    private int rentalDays;
    private UUID skiId;
    private SkiStatus skiStatus;
}
