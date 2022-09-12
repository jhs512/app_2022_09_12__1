package com.ll.exam.app_2022_09_12__1.app.member.entity;


import com.ll.exam.app_2022_09_12__1.app.base.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

import static lombok.AccessLevel.PROTECTED;

@Entity
@SuperBuilder
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member extends BaseEntity {
    private String username;
    private String email;
    private String password;
    private String name;

}
