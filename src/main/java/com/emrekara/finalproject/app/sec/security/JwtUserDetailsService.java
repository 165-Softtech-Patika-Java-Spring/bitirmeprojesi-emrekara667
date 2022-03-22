package com.emrekara.finalproject.app.sec.security;

import com.emrekara.finalproject.app.user.entity.UsUser;
import com.emrekara.finalproject.app.user.service.entityservice.UsUserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UsUserEntityService usUserEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsUser usUser = usUserEntityService.findUsUserByUserNameWithControl(username);

        return JwtUserDetails.create(usUser);
    }

    public UserDetails loadUserByUserId(Long id) {

        UsUser usUser = usUserEntityService.getByIdWithControl(id);

        return JwtUserDetails.create(usUser);
    }
}
