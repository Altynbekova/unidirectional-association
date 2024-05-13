package com.altynbekova.top.unidirectionalassociation.dao;

import com.altynbekova.top.unidirectionalassociation.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("select p from Project p where p.name like ':name'")
    List<Project> find(@Param("name") String name);
}
