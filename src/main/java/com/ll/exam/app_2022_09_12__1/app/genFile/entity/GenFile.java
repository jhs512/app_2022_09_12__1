package com.ll.exam.app_2022_09_12__1.app.genFile.entity;

import com.ll.exam.app_2022_09_12__1.app.base.entity.BaseEntity;
import com.ll.exam.app_2022_09_12__1.app.genFile.service.GenFileService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import java.io.File;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GenFile extends BaseEntity {
    private String relTypeCode;
    private long relId;
    private String typeCode;
    private String type2Code;
    private String fileExtTypeCode;
    private String fileExtType2Code;
    private long fileSize;
    private int fileNo;
    private String fileExt;
    private String fileDir;
    private String originFileName;

    public void deleteStorageFile() {
        new File(getFilePath()).delete();
    }

    private String getFilePath() {
        return getStoragePath() + "/" + getRelTypeCode() + "/" + getFileDir() + "/" + getFileName();
    }

    private String getStoragePath() {
        return GenFileService.GEN_FILE_DIR_PATH;
    }

    private String getFileName() {
        return getId() + "." + getFileExt();
    }
}
