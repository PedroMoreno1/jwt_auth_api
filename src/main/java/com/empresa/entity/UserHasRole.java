package com.empresa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_roles")
public class UserHasRole {
    //Clave primaria compuesta, definida en la clase "UserHasRolePk"(@Embeddable)
    @EmbeddedId
    private UserHasRolePk id;

    @ManyToOne(fetch = FetchType.LAZY)
    // @MapsId indica que este campo forma parte de la clave primaria (userId)
    @MapsId("userId") // mapea la FK a userId del ID enbebido, usa el userId del EmbeddedId y vincula a esta relación con User
    @JoinColumn(name = "user_id") // Define la columna física en la base de datos
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Role role;

    @CreationTimestamp
    @Column(name = "assigned_at", updatable = false)
    private LocalDateTime assignedAt;

    //Creo el constructor con parametros manualmente
    public UserHasRole(User user, Role role){
        this.user = user;
        this.role = role;

        //Se crea la clave compuesta con los IDs de las entidades user y role
        this.id = new UserHasRolePk(user.getUserId(), role.getRoleId());
    }
}

/*
 * Esta clase representa una relación muchos-a-muchos entre User y Role,
 * con un campo adicional "assignedAt" para auditoría.
 * Por eso se modela como una entidad intermedia explícita.
 *
 *-------------------------------------------------------------------------------
 *
 * La anotación @ManyToOne en la clase UserHasRole es el lado dueño de la relación.
 * Es decir, la tabla user_roles tiene las claves foráneas (user_id, role_id), y por tanto
 * debe indicarse allí cómo hacer el join hacia User y Role.

 * En JPA, solo un lado de la relación debe ser el dueño (el que define @JoinColumn).
*/