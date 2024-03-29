package com.davy.restapi.card.repository;

import com.davy.restapi.address.repository.CustomAddressRepository;
import com.davy.restapi.card.entity.CustomerCardEntity;
import com.davy.restapi.shared.repository.CrudRepositoryImpl;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class CustomCardRepositoryImpl extends CrudRepositoryImpl<CustomerCardEntity>
        implements CustomAddressRepository {

    public CustomCardRepositoryImpl(EntityManager entityManager) {
        super(entityManager, CustomerCardEntity.class);
    }
}

