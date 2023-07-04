package org.uy1.uemanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.uy1.uemanagement.enums.TypeSupport;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class Supports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private TypeSupport typeSupport;
    private String documentDirectory;
    private String videoDirectory;
    private String linkDirectory;
    private String auteur;
}
