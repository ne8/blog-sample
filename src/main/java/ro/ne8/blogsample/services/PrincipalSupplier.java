package ro.ne8.blogsample.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface PrincipalSupplier {

    String getPrincipal();

    UserDetails getSpringContextUserDetails();
}
