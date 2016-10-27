package com.hys.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.hys.dao.UserRoleDao;
import com.hys.service.UserService;

@Repository
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;
	@Autowired
	UserRoleDao userRoleDao;
	static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		com.hys.entity.User cu = null;
		try {
			 //cu = userService.getUserByName(username);
			cu = new com.hys.entity.User();
			cu.setUsername("admin");
			cu.setPassword("admin");
			cu.setId("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(cu==null){
			 throw new UsernameNotFoundException("User details not found with this username: " + username);
		}
		List<SimpleGrantedAuthority> authList = getAuthorities(cu.getId());
		//User u = new User(username,cu.getPassword(), authList);
		User u = new User(username, passwordEncoder.encode(cu.getPassword()), authList);
		return u;
	}
	
	private List<SimpleGrantedAuthority> getAuthorities(String userId){
		List<SimpleGrantedAuthority> authList = new ArrayList<SimpleGrantedAuthority>();
		/*List<UserRole> urs = userRoleDao.getRoleByUserId(userId);
	    for(UserRole r :urs){
	    	authList.add(new SimpleGrantedAuthority(r.getRole()));
	    }*/
		authList.add(new SimpleGrantedAuthority("ADMIN"));
        return authList;
	}

}
