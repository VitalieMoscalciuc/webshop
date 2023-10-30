package com.vmoscalciuc.services.repository;

import com.vmoscalciuc.services.model.ServicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServicesEntity,Long> {
}
