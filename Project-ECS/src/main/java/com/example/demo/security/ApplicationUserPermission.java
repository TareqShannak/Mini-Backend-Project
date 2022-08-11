package com.example.demo.security;

public enum ApplicationUserPermission {
	RESOURCE_READ("resource:read"),
	RESOURCE_WRITE("resource:write"),
	USER_READ("user:read");
	
	private final String permission;

	private ApplicationUserPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
	
	
}
