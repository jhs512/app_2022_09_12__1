package com.ll.exam.app_2022_09_12__1.app.genFile.dto;

import com.ll.exam.app_2022_09_12__1.app.genFile.entity.GenFile;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RelGenFiles {
    private final List<GenFile> genFiles;

    public GenFile get(String typeCode, String type2Code, int fileNo) {
        return genFiles
                .stream()
                .filter(genFile -> genFile.getTypeCode().equals(typeCode))
                .filter(genFile -> genFile.getType2Code().equals(type2Code))
                .filter(genFile -> genFile.getFileNo() == fileNo)
                .findFirst().orElse(null);
    }

    public List<GenFile> all() {
        return genFiles;
    }
}
