package com.example.demo.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.security.ApplicationUserRole;
import com.google.common.collect.Lists;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {

	private final PasswordEncoder passwordEncoder;

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
		List<ApplicationUser> applicationusers = Lists.newArrayList(
				new ApplicationUser("tareq", passwordEncoder.encode("password"),
						ApplicationUserRole.USER.getGrantedAuthorities(), true, true, true, true),
				new ApplicationUser("anas", passwordEncoder.encode("password"),
						ApplicationUserRole.USER.getGrantedAuthorities(), true, true, true, true),
				new ApplicationUser("wisam", passwordEncoder.encode("password123"),
						ApplicationUserRole.ADMIN.getGrantedAuthorities(), true, true, true, true));

		return applicationusers;

	}

}
