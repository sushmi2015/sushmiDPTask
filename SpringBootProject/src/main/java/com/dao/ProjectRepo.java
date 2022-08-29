package com.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.ProjectDetailsDO;

@Repository
public interface ProjectRepo  extends JpaRepository<ProjectDetailsDO, Integer> {

}
