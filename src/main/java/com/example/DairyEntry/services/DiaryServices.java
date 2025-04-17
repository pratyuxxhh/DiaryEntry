package com.example.DairyEntry.services;

import com.example.DairyEntry.pojos.DiaryPOJO;
import com.example.DairyEntry.pojos.UserDetails;
import com.example.DairyEntry.repository.DiaryRepo;
import com.example.DairyEntry.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiaryServices {
    @Autowired
    private DiaryRepo diaryRepo;
    @Autowired
    private UserServices userServices;
    @Autowired
    private UserRepo userRepo;

    public void saveThisDiary(DiaryPOJO d) {

        String currentUser=userServices.getCurrentUser();
        UserDetails user = userRepo.findByUsername(currentUser).get();
        diaryRepo.save(d);
        user.getDiarylist().add(d);
        userServices.saveThisUser(user);
    }


    public DiaryPOJO getDiaryByName(String diaryName) {
        return diaryRepo.findByDiaryName(diaryName).get();
    }

    public void deleteThisDiary(DiaryPOJO diary) {
        Optional<UserDetails> userOptional = userRepo.findByUsername(userServices.getCurrentUser());

        if (userOptional.isPresent()) {
            UserDetails user = userOptional.get();
            user.getDiarylist().remove(diary);
            userRepo.save(user);
            diaryRepo.delete(diary);
        }
    }

    public boolean isDiaryNameUnique(String diaryName) {
        return getDiaryByName(diaryName) == null;
    }

    public boolean existsByDiaryName(String diaryName) {return diaryRepo.existsByDiaryName(diaryName);
    }
}
