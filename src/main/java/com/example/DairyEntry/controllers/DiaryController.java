package com.example.DairyEntry.controllers;

import com.example.DairyEntry.pojos.DiaryPOJO;
import com.example.DairyEntry.pojos.UserDetails;
import com.example.DairyEntry.repository.UserRepo;
import com.example.DairyEntry.services.DiaryServices;
import com.example.DairyEntry.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/diary")
public class DiaryController {
    @Autowired
    private UserServices userServices;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private DiaryServices diaryServices;

    @GetMapping
    public List<DiaryPOJO> getUserDiary(){
        String currentUser=userServices.getCurrentUser();
        UserDetails user = userRepo.findByUsername(currentUser).get();
         return userServices.getUsersDiaries(user);
    }
    @GetMapping("/{diaryname}")
    public DiaryPOJO getDiary(@PathVariable String diaryname){
        return diaryServices.getCurrentUserDiary(diaryname);
    }

    @PostMapping("/post")
    public ResponseEntity<DiaryPOJO> postMyDiary(@RequestBody DiaryPOJO d){
        diaryServices.saveThisDiary(d);
        return new ResponseEntity<>(d , HttpStatus.ACCEPTED);

    }
    @DeleteMapping("/{diaryname}")
    public ResponseEntity<DiaryPOJO> deleteMyDiary(@PathVariable String diaryname){
        DiaryPOJO diary=diaryServices.getDiaryByName(diaryname);
        diaryServices.deleteThisDiary(diary);
        return new ResponseEntity<>(diary , HttpStatus.OK);
    }
    @GetMapping("/checkName")
    public boolean isDiaryAvailable(@RequestParam String diaryName) {
        return diaryServices.existsByDiaryName(diaryName);
    }

    // delete diary funciton

}
