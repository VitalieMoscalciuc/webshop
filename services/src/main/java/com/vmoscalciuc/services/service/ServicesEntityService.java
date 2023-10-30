package com.vmoscalciuc.services.service;

import com.vmoscalciuc.services.dto.ServiceRequest;
import com.vmoscalciuc.services.dto.ServiceResponse;

import java.util.List;

public interface ServicesEntityService {
    void addService(ServiceRequest serviceRequest);
    ServiceResponse getServiceById(Long serviceId);
    List<ServiceResponse> getAllServices();
    ServiceResponse updateService(Long id, ServiceRequest serviceRequest);
    void deleteService(Long id);
}
