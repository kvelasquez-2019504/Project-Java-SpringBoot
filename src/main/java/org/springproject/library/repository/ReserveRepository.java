package org.springproject.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import org.springproject.library.entity.ReserveEntity;

@Service
public interface ReserveRepository extends MongoRepository<ReserveEntity,String> {
}
