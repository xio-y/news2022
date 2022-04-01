package com.edu.hbpu.news2022.entity;


import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("followUser")
public class FollowUser {
    private ObjectId id;
    private Long userId;
    private Long followUserId;
    private Long created;
}
