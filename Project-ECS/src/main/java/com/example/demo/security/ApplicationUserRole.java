package com.example.demo.security;

import static com.example.demo.security.ApplicationUserPermission.FEEDBACK_WRITE;
import static com.example.demo.security.ApplicationUserPermission.RESOURCE_READ;
import static com.example.demo.security.ApplicationUserPermission.RESOURCE_WRITE;
import static com.example.demo.security.ApplicationUserPermission.USER_READ;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

public enum ApplicationUserRole {
	USER(Sets.newHashSet(RESOURCE_READ, FEEDBACK_WRITE)),
	ADMIN(Sets.newHashSet(RESOURCE_READ, RESOURCE_WRITE, USER_READ));
	
	private final Set<ApplicationUserPermission> permissions;

	private ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
		this.permissions = permissions;
	}

	public Set<ApplicationUserPermission> getPermissions() {
		return permissions;
	}
	
	public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
		Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
		.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
		.collect(Collectors.toSet());
		
		// permissions.add(new SimpleGrantedAuthority(this.name()));
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return permissions;
	}
	
	
}
