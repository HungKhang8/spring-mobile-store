package com.fa.springmobilestore.authentication;

import com.fa.springmobilestore.dao.UserDAO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MobileStoreDBAuthentication implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        com.fa.springmobilestore.entity.User user = userDAO.findUser(userName);
        System.out.println(user);

        if (user == null) {
            throw new UsernameNotFoundException("User "
                    + userName + " was not found in the database.");
        }

        /* Admin */
        String role = user.getUserRole();
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);

        grantList.add(authority);

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        UserDetails userDetails = (UserDetails) new User(
                userName, user.getPassword(), enabled,
                accountNonExpired, credentialsNonExpired, accountNonLocked,
                grantList
        );
        
        return userDetails;
    }

}
