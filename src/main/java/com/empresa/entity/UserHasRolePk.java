package com.empresa.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode //Para que JPA compare correctamente la clave compuesta
@Embeddable //Indica que esta clase puede incrustarse como ID
public class UserHasRolePk implements Serializable {

    private Long userId;
    private Long roleId;
}
