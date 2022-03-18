package com.emrekara.finalproject.app.user.dao;

import com.emrekara.finalproject.app.user.entity.UsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsUserDao extends JpaRepository<UsUser, Long> {
}
