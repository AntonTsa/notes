package org.example.notes.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "notes")
@NoArgsConstructor
@AllArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(length = 100)
    private String title;
    @Column(length = 1000)
    private String content;
}
