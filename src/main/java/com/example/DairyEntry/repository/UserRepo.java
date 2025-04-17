package com.example.DairyEntry.repository;

import com.example.DairyEntry.pojos.UserDetails;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserRepo extends MongoRepository<UserDetails , ObjectId> {
    Optional<UserDetails> findByUsername(String username);

    boolean existsByUsername(String username);
}
