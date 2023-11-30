package com.Megdana.Dance.with.Megdana.repositories;

import com.Megdana.Dance.with.Megdana.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);


}

