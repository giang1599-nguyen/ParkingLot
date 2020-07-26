package model;

import java.sql.Date;

public class User {
	int id;
	String name;
	String email;
	String fullname;
	String password;
	String address;
	String phone;
	int group;
	String gender;
	int active;
	Date created_at;
	Date updated_at;

	@Override
	public String toString() {
		return "User{" + "id:" + id + ", name:'" + name + '\'' + ", email:'" + email + '\'' + ", fullname:'" + fullname
				+ '\'' + ", password:'" + password + '\'' + ", address:'" + address + '\'' + ", phone:'" + phone + '\''
				+ ", group:" + group + ", gender:'" + gender + '\'' + ", active:" + active + ", created_at:"
				+ created_at + ", updated_at:" + updated_at + '}';
	}

	public User() {
	}

	public User(String name, String email, String password, String phone) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
	}

	public boolean checkUser(String username, String password) {

	}

	public boolean isUserExist(String username, String password) {

	}

	public User getUser(String email) {

	}

	public User getUser(int idUser) {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public User(int id, String name, String email, String avatar, String fullname, String password, String address,
			String phone, int group, String gender, int active, Date created_at, Date updated_at) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.fullname = fullname;
		this.password = password;
		this.address = address;
		this.phone = phone;
		this.group = group;
		this.gender = gender;
		this.active = active;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
}
