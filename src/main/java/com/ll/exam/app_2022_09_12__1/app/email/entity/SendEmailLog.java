package com.ll.exam.app_2022_09_12__1.app.email.entity;

import com.ll.exam.app_2022_09_12__1.app.base.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
public class SendEmailLog extends BaseEntity {
    private String resultCode;
    private String message;
    private String email;
    private String title;
    private String body;
    private LocalDateTime sendEndDate;
    private LocalDateTime failDate;
}
