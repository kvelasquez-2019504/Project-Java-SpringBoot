package org.springproject.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springproject.library.entity.BookEntity;


@Repository
public interface BookRepository extends MongoRepository<BookEntity, String> {
}
