package com.cuong02n.general.crawl.qldthust.repository;

import com.cuong02n.general.crawl.qldthust.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HustStudentRepository extends JpaRepository<Student, String> {
}
