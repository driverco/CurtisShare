package com.driverco.security.model;

public class AppUser {
	 
    private Long userId;
    private String userName;
    private String encryptedPassword;
    private String firstName;
    private String lastName;
    private String email;
    private String cellphone;
    
    public AppUser(String userName, String encryptedPassword, String firstName, String lastName, String email,
			String cellphone) {
		super();
		this.userName = userName;
		this.encryptedPassword = encryptedPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.cellphone = cellphone;
	}

	public AppUser() {
 
    }
 
    public AppUser(String userName, String encryptedPassword) {
        this.userName = userName;
        this.encryptedPassword = encryptedPassword;
    }
 
    public Long getUserId() {
        return userId;
    }
 
    public void setUserId(Long userId) {
        this.userId = userId;
    }
 
    public String getUserName() {
        return userName;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    public String getEncryptedPassword() {
        return encryptedPassword;
    }
 
    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }
 
    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Override
    public String toString() {
        return this.userName + "/" + this.encryptedPassword;
    }
 
}
