package com.ll.exam.app_2022_09_12__1.app.security;

import com.ll.exam.app_2022_09_12__1.app.member.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MemberContext extends User implements OAuth2User {

    private Map<String, Object> attributes;
    private String nameAttributeKey;

    public MemberContext(Member member, Set<GrantedAuthority> authorities) {
        super(member.getUsername(), member.getPassword(), authorities);
    }

    public MemberContext(Member member, Set<GrantedAuthority> authorities, Map<String, Object> attributes, String nameAttributeKey) {
        this(member, authorities);

        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return super.getAuthorities().stream().collect(Collectors.toSet());
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public String getName() {
        return this.getAttribute(this.nameAttributeKey).toString();
    }
}
