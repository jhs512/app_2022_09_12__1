package com.ll.exam.app_2022_09_12__1.app.genFile.repository;

import com.ll.exam.app_2022_09_12__1.app.genFile.entity.GenFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface GenFileRepository extends JpaRepository<GenFile, Long> {
    List<GenFile> findByRelTypeCodeAndRelId(String relTypeCode, Long relId);

    @Modifying(clearAutomatically = true)
    void deleteByRelTypeCodeAndRelId(String member, Long id);
}
