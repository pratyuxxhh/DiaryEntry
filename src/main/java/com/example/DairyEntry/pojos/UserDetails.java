package com.example.DairyEntry.pojos;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "user_data")
public class UserDetails {

    @Id
    private ObjectId id;
    private String username;
    private String password;
    private String email;

    @DBRef
    private List<DiaryPOJO> diarylist= new ArrayList<>();

    public List<DiaryPOJO> getDiarylist() {
        return diarylist;
    }

    public void setDiarylist(List<DiaryPOJO> diarylist) {
        this.diarylist = diarylist;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
