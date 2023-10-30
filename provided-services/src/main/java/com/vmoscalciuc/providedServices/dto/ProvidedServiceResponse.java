package com.vmoscalciuc.providedServices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProvidedServiceResponse {
    private Long id;
    private Long serviceId;
    private Long customerId;
    private Integer quantity;
    private Double price;
}
