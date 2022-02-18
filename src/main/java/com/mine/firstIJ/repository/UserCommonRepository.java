package com.mine.firstIJ.repository;

import com.mine.firstIJ.repository.entity.UserCommon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCommonRepository extends JpaRepository<UserCommon, Integer> {
}
