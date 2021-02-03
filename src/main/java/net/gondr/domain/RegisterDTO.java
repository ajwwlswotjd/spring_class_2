package net.gondr.domain;

import org.springframework.web.multipart.MultipartFile;

public class RegisterDTO {
	private String userid;
	private String name;
	private String password;
	private String password_check;
	private MultipartFile profileImg;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword_check() {
		return password_check;
	}
	@Override
	public String toString() {
		return "RegisterDTO [userid=" + userid + ", name=" + name + ", password=" + password + ", password_check="
				+ password_check + ", profileImg=" + profileImg + "]";
	}
	public void setPassword_check(String password_check) {
		this.password_check = password_check;
	}
	public MultipartFile getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(MultipartFile profileImg) {
		this.profileImg = profileImg;
	}
	
	
}
