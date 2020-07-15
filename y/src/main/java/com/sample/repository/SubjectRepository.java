package com.sample.repository;

import com.sample.model.Subject;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findByPublished(boolean published);
    List<Subject> findByTitleContaining(String title);

}