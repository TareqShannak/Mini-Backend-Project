package com.example.demo.security;

public enum ApplicationUserPermission {
	RESOURCE_READ("resource:read"),
	RESOURCE_WRITE("resource:write"),
	FEEDBACK_WRITE("feedback:write"),
	FEEDBACK_READ("feedback:read"),
	USER_READ("user:read"),
	USER_WRITE("user:write"),
	RESOURCE_WITH_CONTRACT_READ("resource_with_contract:read");
	
	private final String permission;

	private ApplicationUserPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
	
	
}
