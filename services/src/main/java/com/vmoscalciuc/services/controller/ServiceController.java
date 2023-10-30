package com.vmoscalciuc.services.controller;

import com.vmoscalciuc.services.dto.ServiceRequest;
import com.vmoscalciuc.services.dto.ServiceResponse;
import com.vmoscalciuc.services.service.ServicesEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/service")
@RequiredArgsConstructor
public class ServiceController {
    private final ServicesEntityService servicesEntityService;

    @PostMapping
    public void addService(@RequestBody ServiceRequest serviceRequest) {
        servicesEntityService.addService(serviceRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ServiceResponse getServiceById(@PathVariable Long id) {
        return servicesEntityService.getServiceById(id);
    }

    @GetMapping
    public List<ServiceResponse> getAllServices() {
        return servicesEntityService.getAllServices();
    }

    @PutMapping("/{id}")
    public ServiceResponse updateService(@PathVariable Long id, @RequestBody ServiceRequest serviceRequest) {
        return servicesEntityService.updateService(id, serviceRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable Long id) {
        servicesEntityService.deleteService(id);
    }
}
