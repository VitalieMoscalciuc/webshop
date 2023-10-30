package com.vmoscalciuc.providedServices.repository;

import com.vmoscalciuc.providedServices.model.ProvidedServicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProvidedServicesRepository extends JpaRepository<ProvidedServicesEntity,Long> {
    @Modifying
    @Query(value = "UPDATE t_provided_services " +
            "SET service_id = ?2, customer_id = ?3, quantity = ?4, price = ?5 " +
            "WHERE id = ?1", nativeQuery = true)
    @Transactional
    void updateProvidedService(Long id, Long serviceId, Long customerId, Integer quantity, Double price);

    @Modifying
    @Query(value = "DELETE FROM t_provided_services WHERE id = ?1", nativeQuery = true)
    @Transactional
    void deleteProvidedService(Long id);
}
