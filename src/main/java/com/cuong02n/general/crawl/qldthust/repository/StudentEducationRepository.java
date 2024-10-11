package com.cuong02n.general.crawl.qldthust.repository;

import com.cuong02n.general.crawl.qldthust.entity.StudentEducationData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentEducationRepository extends JpaRepository<StudentEducationData,StudentEducationData.Key> {
}
