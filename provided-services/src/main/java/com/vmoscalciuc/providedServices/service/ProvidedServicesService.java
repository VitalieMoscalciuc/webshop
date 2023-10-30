package com.vmoscalciuc.providedServices.service;

import com.vmoscalciuc.providedServices.dto.ProvidedServiceRequest;
import com.vmoscalciuc.providedServices.dto.ProvidedServiceResponse;

import java.util.List;

public interface ProvidedServicesService {
    void addProvidedService(ProvidedServiceRequest providedServiceRequest);
    ProvidedServiceResponse findProvidedServicesById(Long id);
    List<ProvidedServiceResponse> getAllProvidedServices();
    void updateProvidedService(Long id, ProvidedServiceRequest providedServiceRequest);
    void deleteProvidedService(Long id);
}
