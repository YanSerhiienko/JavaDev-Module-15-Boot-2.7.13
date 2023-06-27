package com.example.JavaDevModule15Boot273.repository;


import com.example.JavaDevModule15Boot273.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
