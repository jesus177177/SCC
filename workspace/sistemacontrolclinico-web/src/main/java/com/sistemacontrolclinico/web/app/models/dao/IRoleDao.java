package com.sistemacontrolclinico.web.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemacontrolclinico.web.app.models.entity.Role;

public interface IRoleDao extends JpaRepository<Role, Long> {

}
