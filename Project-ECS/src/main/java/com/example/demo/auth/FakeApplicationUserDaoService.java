package com.example.demo.auth;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Manager;
import com.example.demo.entities.User;
import com.example.demo.security.ApplicationUserRole;
import com.example.demo.services.ManagerService;
import com.example.demo.services.UserService;
import com.google.common.collect.Lists;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {

	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ManagerService managerService;

	private List<ApplicationUser> applicationusers;

	public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
		// TODO Auto-generated method stub
		return getApplicationUsers()
				.stream()
				.filter(ApplicationUser -> username.equals(ApplicationUser.getUsername()))
				.findFirst();
	}

	private List<ApplicationUser> getApplicationUsers() {
		
		applicationusers = Lists.newArrayList();
		for(User user: userService.getAllUsers()) {
			applicationusers.add(new ApplicationUser(user.getEmail(), user.getPassword(), ApplicationUserRole.USER.getGrantedAuthorities(), true, true, true, true));
		}
		for(Manager manager: managerService.getAllManagers()) {
			applicationusers.add(new ApplicationUser(manager.getEmail(), manager.getPassword(), ApplicationUserRole.MANAGER.getGrantedAuthorities(), true, true, true, true));
		}
		applicationusers.add(new ApplicationUser("a@b.c", passwordEncoder.encode("admin"), ApplicationUserRole.ADMIN.getGrantedAuthorities(), true, true, true, true));

		return applicationusers;

	}

}
