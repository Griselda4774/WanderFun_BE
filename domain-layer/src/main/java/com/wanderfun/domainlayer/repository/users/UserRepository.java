package com.wanderfun.domainlayer.repository.users;

import com.wanderfun.domainlayer.model.users.User;
import com.wanderfun.domainlayer.repository.BaseRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {
    Optional<User> findByAccountId(Long accountId);
}
