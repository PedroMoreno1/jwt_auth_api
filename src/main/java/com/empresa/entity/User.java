package com.empresa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor // Para que JPA pueda instanciar la clase
@AllArgsConstructor // Para pruebas o carga manual (se usa en esta ocacion)
@Builder // Permite construir objetos con el patrón builder
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String userName;
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false, unique = true, length = 8)
    private String dni;
    @Column(length = 9)
    private String phone;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active")
    private Boolean isActive = true;

    /*
     * Relación inversa con UserHasRole
     * mappedBy = "user" -> hace referencia al campo en UserHasRole que contiene la relación
     * cascade = ALL -> operaciones como persist, merge, remove se propagan a los roles
     * orphanRemoval = true -> si se elimina la relación del Set, se elimina el registro de la tabla intermedia
     */

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserHasRole> userHasRoles = new HashSet<>();

}
