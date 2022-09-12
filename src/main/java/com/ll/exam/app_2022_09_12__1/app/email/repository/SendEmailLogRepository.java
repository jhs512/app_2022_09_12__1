package com.ll.exam.app_2022_09_12__1.app.email.repository;

import com.ll.exam.app_2022_09_12__1.app.email.entity.SendEmailLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SendEmailLogRepository extends JpaRepository<SendEmailLog, Long> {
}
