package com.example.demo.src.file;

import com.example.demo.src.file.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File,Long> {
}
