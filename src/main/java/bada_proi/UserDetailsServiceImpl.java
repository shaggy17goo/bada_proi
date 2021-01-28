package bada_proi;

import bada_proi.dao.AppRoleDAO;
import bada_proi.dao.AppUserDAO;
import bada_proi.entity.AppRole;
import bada_proi.entity.AppUser;
import bada_proi.utils.ProjectConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AppUserDAO appUserDAO;

    @Autowired
    private AppRoleDAO appRoleDAO;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(ProjectConstants.URL);
        dataSource.setUsername(ProjectConstants.USERNAME);
        dataSource.setPassword(ProjectConstants.PASSWORD);
        dataSource.setDriverClassName(ProjectConstants.DRIVERCLASSNAME);

        appUserDAO = new AppUserDAO(new JdbcTemplate(dataSource));
        appRoleDAO = new AppRoleDAO(new JdbcTemplate(dataSource));
        AppUser appUser;
        try {
            appUser = this.appUserDAO.get(userName);
        }
        catch (Exception e){
            appUser = null;
        }

        if (appUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        System.out.println("Found User: " + appUser);

        // [ROLE_USER, ROLE_ADMIN,..]
        AppRole role = this.appRoleDAO.getRoleName(appUser.getUserId());
        String roleName = role.getRoleName();
        System.out.println(role);

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleName);
        List<GrantedAuthority> grants = Collections.singletonList(grantedAuthority);


        return (UserDetails) new User(appUser.getUsername(), appUser.getEncryptedPassword(), grants);
    }

}