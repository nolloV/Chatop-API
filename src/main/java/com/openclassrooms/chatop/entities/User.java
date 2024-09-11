package com.openclassrooms.chatop.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

// Déclare que cette classe est une entité JPA et mappe la table "users"
@Table(name = "users")
@Entity
public class User implements UserDetails {

    // Identifiant unique de l'utilisateur, généré automatiquement
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @JsonProperty("user_id")
    private Integer id;

    // Nom de l'utilisateur, ne peut pas être nul
    @Column(nullable = false)
    private String name;

    // Adresse email de l'utilisateur, doit être unique et ne peut pas être nulle
    @Column(unique = true, length = 100, nullable = false)
    private String email;

    // Mot de passe de l'utilisateur, ne peut pas être nul
    @Column(nullable = false)
    private String password;

    // Date de création de l'utilisateur, générée automatiquement lors de l'insertion
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    // Date de mise à jour de l'utilisateur, mise à jour automatiquement lors de chaque modification
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    // Retourne les autorités accordées à l'utilisateur (aucune dans ce cas)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    // Retourne le mot de passe de l'utilisateur
    @Override
    public String getPassword() {
        return password;
    }

    // Retourne le nom d'utilisateur (dans ce cas, l'email)
    @Override
    public String getUsername() {
        return email;
    }

    // Indique si le compte de l'utilisateur n'a pas expiré
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Indique si le compte de l'utilisateur n'est pas verrouillé
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Indique si les informations d'identification de l'utilisateur n'ont pas expiré
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Indique si l'utilisateur est activé
    @Override
    public boolean isEnabled() {
        return true;
    }

    // Getter pour l'identifiant de l'utilisateur
    public Integer getId() {
        return id;
    }

    // Setter pour l'identifiant de l'utilisateur
    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    // Getter pour le nom de l'utilisateur
    public String getName() {
        return name;
    }

    // Setter pour le nom de l'utilisateur
    public User setName(String name) {
        this.name = name;
        return this;
    }

    // Getter pour l'email de l'utilisateur
    public String getEmail() {
        return email;
    }

    // Setter pour l'email de l'utilisateur
    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    // Setter pour le mot de passe de l'utilisateur
    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    // Getter pour la date de création de l'utilisateur
    public Date getCreatedAt() {
        return createdAt;
    }

    // Setter pour la date de création de l'utilisateur
    public User setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    // Getter pour la date de mise à jour de l'utilisateur
    public Date getUpdatedAt() {
        return updatedAt;
    }

    // Setter pour la date de mise à jour de l'utilisateur
    public User setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    // Méthode toString pour afficher les informations de l'utilisateur
    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", email='" + email + '\''
                + ", password='" + password + '\''
                + ", createdAt=" + createdAt
                + ", updatedAt=" + updatedAt
                + '}';
    }
}
