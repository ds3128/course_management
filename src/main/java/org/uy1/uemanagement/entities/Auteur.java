package org.uy1.uemanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class Auteur {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    private String firstName;
    private String lastName;
    private String tel;
    private String email;
    private String grade;
}
