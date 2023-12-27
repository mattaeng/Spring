package com.mattaeng.mattaengapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mattaeng.mattaengapi.entity.Heart;

public interface HeartRepository extends JpaRepository<Heart, Long> {
}
