package com.myweb.www.security;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class UserDTO {

	String userID;
	String userPassword;
	String userName;
	int userAge;
	String userGender;
	String userEmail;
	String userProfile;
}
