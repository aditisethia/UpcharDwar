package com.upchardwar.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upchardwar.app.entity.Messages;

public interface MessageRepository extends JpaRepository<Messages, Long> {

}
