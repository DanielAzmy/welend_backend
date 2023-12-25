package com.welend.welend.DAO;

import com.welend.welend.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UserDAO extends JpaRepository<User, BigInteger> {

    @Query(value = "select * from user where id = :userId and email = :userEmail",nativeQuery = true)
    User getUserByIdAndEmail(@Param("userId") BigInteger id,@Param("userEmail") String email);
}
