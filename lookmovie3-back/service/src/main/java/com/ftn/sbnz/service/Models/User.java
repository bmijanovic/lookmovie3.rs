package com.ftn.sbnz.service.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "USERS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE USERS SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class User implements UserDetails{
    @Id
	@Column(columnDefinition = "uuid")
	private UUID id;

	@Column(nullable = false)
	private String name;

	@Column(unique = true, nullable = false)
	private String email;

	private Integer loginAttempts = 0;

	private String password;

	private Boolean deleted = Boolean.FALSE;

	@Column(name = "EMAIL_VERIFIED")
	private Boolean emailVerified = true;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<Role> roles;


	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> permissions = new ArrayList<>(20);
		for (Role role : this.roles) {
			permissions.addAll(role.getPrivileges());
		}
		return permissions;
	}

	public Boolean hasRole(String roleName) {
		for (Role role : this.roles) {
			if (role.getName().equals(roleName)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return loginAttempts < 3;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return emailVerified;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", loginAttempts=" + loginAttempts
				+ ", password=" + password + ", deleted=" + deleted + ", emailVerified=" + emailVerified + ", roles="
				+ roles + "]";
	}

}

