package com.mine.firstIJ.repository;

import com.mine.firstIJ.repository.entity.UserCommon;
import com.mine.firstIJ.repository.entity.UserCommonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCommonRepository extends JpaRepository<UserCommon, UserCommonId> {
    List<UserCommon> findByUsername(String username);

    List<UserCommon> findTopByOrderByIdDesc();
}
