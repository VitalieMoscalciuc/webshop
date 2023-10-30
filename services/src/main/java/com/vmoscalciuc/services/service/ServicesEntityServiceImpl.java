package com.vmoscalciuc.services.service;

import com.vmoscalciuc.services.model.ServicesEntity;
import com.vmoscalciuc.services.repository.ServiceRepository;
import com.vmoscalciuc.services.dto.ServiceRequest;
import com.vmoscalciuc.services.dto.ServiceResponse;
import com.vmoscalciuc.services.exception.ServiceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ServicesEntityServiceImpl implements ServicesEntityService{
    private final ServiceRepository serviceRepository;
    private final ModelMapper modelMapper;
    @Override
    public void addService(ServiceRequest serviceRequest) {
        serviceRepository.save(modelMapper.map(serviceRequest, ServicesEntity.class));
    }

    @Override
    public ServiceResponse getServiceById(Long serviceId) {
        return modelMapper.map(serviceRepository.findById(serviceId).orElseThrow(()
                        -> new ServiceNotFoundException("No Service with Id:" + serviceId+ " is found")),
                ServiceResponse.class);
    }

    @Override
    public List<ServiceResponse> getAllServices() {
        return serviceRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, ServiceResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public ServiceResponse updateService(Long id, ServiceRequest serviceRequest) {
        ServicesEntity services = serviceRepository.findById(id).orElseThrow(()
                -> new ServiceNotFoundException("No Service with Id:" + id + " is found"));
        modelMapper.map(serviceRequest, services);
        ServicesEntity updatedService = serviceRepository.save(services);
        return modelMapper.map(updatedService, ServiceResponse.class);
    }

    @Override
    public void deleteService(Long id) {
        if (!serviceRepository.existsById(id)) {
            throw new ServiceNotFoundException("No Service with Id:" + id + " is found");
        }
        serviceRepository.deleteById(id);
    }
}
