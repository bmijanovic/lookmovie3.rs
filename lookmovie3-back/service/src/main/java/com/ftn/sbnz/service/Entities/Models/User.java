package com.ftn.sbnz.service.Entities.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
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
@Getter
@Setter
public class User implements UserDetails{
    @Id
	@Column(columnDefinition = "uuid")
	private UUID id;

	@Column(nullable = false)
	private String name;

	@Column(unique = true, nullable = false)
	private String email;

	@Column
	private Boolean gotRecommendation = Boolean.FALSE;

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

	@Column(name = "liked_genres")
	private List<String> likedGenres = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_liked_films",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"))
	private List<Film> likedFilms;


	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_watched_films",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"))
	private List<Film> watchedFilms;


	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_liked_directors",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "director_id", referencedColumnName = "id"))
	private List<Director> likedDirectors;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_liked_actors",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id"))
	private List<Actor> likedActors;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_wishlist",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"))
	private List<Film> wishlist;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_recommended_films",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"))
	private List<Film> recommendedFilms;






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
				+ roles + ", recommendation: " + gotRecommendation + "]";
	}
	public User(String name, String email, String password) {
		this.id = UUID.randomUUID();
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = new ArrayList<>();
		this.likedFilms = new ArrayList<>();
		this.watchedFilms = new ArrayList<>();
		this.likedDirectors = new ArrayList<>();
		this.likedActors = new ArrayList<>();
		this.wishlist = new ArrayList<>();
		this.recommendedFilms = new ArrayList<>();
		this.gotRecommendation = false;
		this.emailVerified = true;
		this.loginAttempts = 0;


	}


	public User update(User user) {
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.roles = user.getRoles();
		this.likedGenres = user.getLikedGenres();
		this.likedFilms = user.getLikedFilms();
		this.watchedFilms = user.getWatchedFilms();
		this.likedDirectors = user.getLikedDirectors();
		this.likedActors = user.getLikedActors();
		this.wishlist = user.getWishlist();
		return this;
	}

}

