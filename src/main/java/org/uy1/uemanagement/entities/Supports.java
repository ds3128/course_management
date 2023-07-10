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
    @Enumerated(EnumType.STRING)
    private TypeSupport typeSupport;
    private String linkDirectory;
    @Lob
    @Column(name = "document_content")
    private byte[] documentContent;
    @Lob
    @Column(name = "video_content")
    private byte[] videoContent;
    private String auteur;
    @ManyToOne
    private Course course;
}
