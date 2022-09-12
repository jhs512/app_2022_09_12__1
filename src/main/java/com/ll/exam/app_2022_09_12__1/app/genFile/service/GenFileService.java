package com.ll.exam.app_2022_09_12__1.app.genFile.service;

import com.ll.exam.app_2022_09_12__1.Util;
import com.ll.exam.app_2022_09_12__1.app.genFile.dto.RelGenFiles;
import com.ll.exam.app_2022_09_12__1.app.genFile.entity.GenFile;
import com.ll.exam.app_2022_09_12__1.app.genFile.repository.GenFileRepository;
import com.ll.exam.app_2022_09_12__1.app.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GenFileService {
    public static String GEN_FILE_DIR_PATH;

    @Value("${custom.genFileDirPath}")
    public void setGenFileDirPath(String genFileDirPath) {
        GEN_FILE_DIR_PATH = genFileDirPath;
    }

    @Value("${custom.genFileDirPath}")
    private String genFileDirPath;
    private final GenFileRepository genFileRepository;

    @Transactional
    public GenFile save(Member member, MultipartFile file, String typeCode, String type2Code, int fileNo) {
        String fileDir = Util.date.getNowYearMonthDay();

        String relTypeCode = "member";
        String fileDirPath = genFileDirPath + "/" + relTypeCode + "/" + fileDir;

        String originalFileName = file.getOriginalFilename();
        String fileExt = Util.file.getExtFromFileName(originalFileName);
        String fileExtTypeCode = Util.file.getExtTypeCodeFromFileName(originalFileName);
        String fileExtType2Code = Util.file.getExtType2CodeFromFileName(originalFileName);

        GenFile genFile = GenFile.builder()
                .relTypeCode(relTypeCode)
                .relId(member.getId())
                .fileDir(fileDir)
                .fileExt(fileExt)
                .typeCode(typeCode)
                .type2Code(type2Code)
                .fileExtTypeCode(fileExtTypeCode)
                .fileExtType2Code(fileExtType2Code)
                .originFileName(originalFileName)
                .fileNo(fileNo)
                .fileSize(file.getSize())
                .build();

        genFileRepository.save(genFile);

        try {
            new File(fileDirPath).mkdirs();
            file.transferTo(new File(fileDirPath + "/" + genFile.getId() + "." + fileExt));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return genFile;
    }

    public RelGenFiles getRelGenFiles(Member member) {
        List<GenFile> genFiles = genFileRepository.findByRelTypeCodeAndRelId("member", member.getId());

        return new RelGenFiles(genFiles);
    }

    @Transactional
    public void deleteRelGenFiles(Member member) {
        getRelGenFiles(member)
                .all()
                .stream()
                .forEach(GenFile::deleteStorageFile);

        getRelGenFiles(member)
                .all()
                .stream()
                .forEach(genFileRepository::delete);
    }
}
