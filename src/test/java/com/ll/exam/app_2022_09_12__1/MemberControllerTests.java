package com.ll.exam.app_2022_09_12__1;

import com.ll.exam.app_2022_09_12__1.app.genFile.dto.RelGenFiles;
import com.ll.exam.app_2022_09_12__1.app.genFile.entity.GenFile;
import com.ll.exam.app_2022_09_12__1.app.genFile.service.GenFileService;
import com.ll.exam.app_2022_09_12__1.app.member.controller.MemberController;
import com.ll.exam.app_2022_09_12__1.app.member.entity.Member;
import com.ll.exam.app_2022_09_12__1.app.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class MemberControllerTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private MemberService memberService;

    @Autowired
    private GenFileService genFileService;

    @Test
    @DisplayName("회원가입")
    @Rollback(false)
    public void t1() throws Exception {
        // given
        MockMultipartFile file = TestUtil.mock.file.from("https://picsum.photos/200/300", "1.png");

        // when
        ResultActions resultActions = mvc.perform(
                        multipart("/member/join")
                                .file(file)
                                .param("username", "user1")
                                .param("password", "1234")
                                .param("password_confirm", "1234")
                                .param("email", "email")
                                .characterEncoding("UTF-8"))
                .andDo(print());

        // then
        resultActions
                .andExpect(status().is3xxRedirection())
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("join"))
                .andExpect(redirectedUrl("/"));

        Member member = memberService.findMemberById(1L);
        assertThat(member).isNotNull();

        RelGenFiles relGenFiles = genFileService.getRelGenFiles(member);
        GenFile proFileImgGenFile = relGenFiles.get("common", "profileImg", 1);

        assertThat(proFileImgGenFile).isNotNull();

        genFileService.deleteRelGenFiles(member);
    }
}