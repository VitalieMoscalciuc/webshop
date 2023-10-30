package com.vmoscalciuc.providedServices.service;

import com.vmoscalciuc.providedServices.dto.ProvidedServiceRequest;
import com.vmoscalciuc.providedServices.dto.ProvidedServiceResponse;
import com.vmoscalciuc.providedServices.dto.ServiceResponse;
import com.vmoscalciuc.providedServices.model.ProvidedServicesEntity;
import com.vmoscalciuc.providedServices.repository.ProvidedServicesRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProvidedServicesServiceImpl implements ProvidedServicesService {
    @Value("${customer.service.url}")
    private String customerServiceUrl;
    @Value("${service.service.url}")
    private String serviceServiceUrl;

    private final ProvidedServicesRepository providedServicesRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;

    @Override
    public void addProvidedService(ProvidedServiceRequest providedServiceRequest) {
        Long customerId = providedServiceRequest.getCustomerId();
        Long serviceId = providedServiceRequest.getServiceId();
        Integer quantity = providedServiceRequest.getQuantity();
        ResponseEntity<ServiceResponse> serviceResponse = getDtoResponseEntity(serviceId);
        checkStatus(providedServiceRequest, customerId, serviceId, quantity, serviceResponse);
    }

    private Double calculatePrice(Double servicePrice, Integer quantity) {
        return servicePrice * quantity;
    }

    @Override
    public ProvidedServiceResponse findProvidedServicesById(Long id) {
        return modelMapper.map(providedServicesRepository.findById(id).orElseThrow(),ProvidedServiceResponse.class);
    }

    @Override
    public List<ProvidedServiceResponse> getAllProvidedServices() {
        return providedServicesRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, ProvidedServiceResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public void updateProvidedService(Long id, ProvidedServiceRequest providedServiceRequest) {
        providedServicesRepository.updateProvidedService(id,providedServiceRequest.getServiceId(),
                providedServiceRequest.getCustomerId(),
                providedServiceRequest.getQuantity(),
                providedServiceRequest.getPrice());
        Long customerId = providedServiceRequest.getCustomerId();
        Long serviceId = providedServiceRequest.getServiceId();
        Integer quantity = providedServiceRequest.getQuantity();

        ResponseEntity<ServiceResponse> serviceResponse = getDtoResponseEntity(serviceId);

        checkStatus(providedServiceRequest, customerId, serviceId, quantity, serviceResponse);
    }

    @Override
    public void deleteProvidedService(Long id) {
        providedServicesRepository.deleteProvidedService(id);
    }

    private ResponseEntity<ServiceResponse> getDtoResponseEntity(Long serviceId) {
        ResponseEntity<ServiceResponse> serviceResponse = restTemplate.exchange(
                serviceServiceUrl + "/service/" + serviceId,
                HttpMethod.GET,
                null,
                ServiceResponse.class
        );
        return serviceResponse;
    }

    private void checkStatus(ProvidedServiceRequest providedServiceRequest, Long customerId, Long serviceId, Integer quantity, ResponseEntity<ServiceResponse> serviceResponse) {
        if (checkCustomer(customerId) && checkService(serviceId)) {
            ServiceResponse serviceDetails = serviceResponse.getBody();
            assert serviceDetails != null;
            Double calculatedPrice = calculatePrice(serviceDetails.getPrice(), quantity);
            ProvidedServicesEntity providedService = new ProvidedServicesEntity();
            providedService.setServiceId(serviceId);
            providedService.setCustomerId(providedServiceRequest.getCustomerId());
            providedService.setQuantity(quantity);
            providedService.setPrice(calculatedPrice);
            providedServicesRepository.save(providedService);
        }else{
            throw new IllegalArgumentException("Provided customer or provided service does not exist");
        }
    }

    private boolean checkCustomer(Long customerId){
        ResponseEntity<String> response = restTemplate.getForEntity(
                customerServiceUrl + "/customers/" + customerId, String.class);
        return response.getStatusCode() == HttpStatus.FOUND;
    }

    private boolean checkService(Long serviceId){
        ResponseEntity<String> response = restTemplate.getForEntity(
                serviceServiceUrl + "/service/" + serviceId, String.class);
        return response.getStatusCode() == HttpStatus.FOUND;
    }
}