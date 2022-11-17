package com.company.repository;

import com.company.models.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatusRepository extends JpaRepository<UserStatus,Long> {
    UserStatus findByName(String name);
}

