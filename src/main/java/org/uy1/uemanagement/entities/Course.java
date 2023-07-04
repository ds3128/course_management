package org.uy1.uemanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private int times;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Supports> supports;
    @OneToOne(mappedBy = "course")
    private Auteur auteur;
}
