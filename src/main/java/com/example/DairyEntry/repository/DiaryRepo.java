package com.example.DairyEntry.repository;

import com.example.DairyEntry.pojos.DiaryPOJO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface DiaryRepo extends MongoRepository<DiaryPOJO, ObjectId> {
    Optional<DiaryPOJO> findByDiaryName(String diaryName);

    boolean existsByDiaryName(String diaryName);
}