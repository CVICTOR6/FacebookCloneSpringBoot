package com.cvictor.facebookclonespringboot.repository;

import com.cvictor.facebookclonespringboot.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

}
