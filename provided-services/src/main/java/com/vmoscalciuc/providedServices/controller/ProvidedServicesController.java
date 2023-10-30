package com.vmoscalciuc.providedServices.controller;

import com.vmoscalciuc.providedServices.dto.ProvidedServiceRequest;
import com.vmoscalciuc.providedServices.dto.ProvidedServiceResponse;
import com.vmoscalciuc.providedServices.service.ProvidedServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provided-services")
@RequiredArgsConstructor
public class ProvidedServicesController {
    private final ProvidedServicesService providedServicesService;

    @PostMapping
    public void addProvidedService(@RequestBody ProvidedServiceRequest providedServiceRequest) {
        providedServicesService.addProvidedService(providedServiceRequest);
    }

    @GetMapping("/{id}")
    public ProvidedServiceResponse findProvidedServicesById(@PathVariable Long id) {
        return providedServicesService.findProvidedServicesById(id);
    }

    @GetMapping
    public List<ProvidedServiceResponse> getAllProvidedServices() {
        return providedServicesService.getAllProvidedServices();
    }

    @PutMapping("/{id}")
    public void updateProvidedService(@PathVariable Long id, @RequestBody ProvidedServiceRequest providedServiceRequest) {
        providedServicesService.updateProvidedService(id, providedServiceRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteProvidedService(@PathVariable Long id) {
        providedServicesService.deleteProvidedService(id);
    }
}

