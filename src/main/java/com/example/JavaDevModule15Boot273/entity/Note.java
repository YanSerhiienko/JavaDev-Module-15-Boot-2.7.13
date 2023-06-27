package com.example.JavaDevModule15Boot273.entity;

import javax.persistence.*;
import lombok.Data;

@Table(name = "note")
@Entity
@Data
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
}
