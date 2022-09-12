package com.ll.exam.app_2022_09_12__1.app.member.service;

import com.ll.exam.app_2022_09_12__1.app.email.service.EmailService;
import com.ll.exam.app_2022_09_12__1.app.genFile.service.GenFileService;
import com.ll.exam.app_2022_09_12__1.app.member.entity.Member;
import com.ll.exam.app_2022_09_12__1.app.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final GenFileService genFileService;
    private final EmailService emailService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member findMemberById(long id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Transactional
    public void join(String username, String password, String email, MultipartFile profileImg) {
        Member member = Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();

        memberRepository.save(member);

        if (profileImg != null && !profileImg.isEmpty()) {
            genFileService.save(member, profileImg, "common", "profileImg", 1);
        }

        emailService.sendEmail(member.getEmail(), "가입축하", "가입축하 합니다.");
    }
}
