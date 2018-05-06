package ro.ne8.blogsample.facades;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

class ParentTest {
    static final String TEST_USERNAME = "ne8";
    static final String TEST_PASSWORD = "password";
    private static final String ROLE_PREFIX_REQUIRED_BY_SPRING_SECURITY_CONTEXT = "ROLE_";

    List<GrantedAuthority> getGrantedAuthorities() {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX_REQUIRED_BY_SPRING_SECURITY_CONTEXT + "USER"));
        return authorities;
    }

}
