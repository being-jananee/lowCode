package com.bizBrainz.server.services;

import com.bizBrainz.server.repositories.UserRepository;
import com.bizBrainz.server.services.ce.SessionUserServiceCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SessionUserServiceImpl extends SessionUserServiceCEImpl implements SessionUserService {

    public SessionUserServiceImpl(UserRepository userRepository,
                                  ReactiveRedisOperations<String, Object> redisOperations) {

        super(userRepository, redisOperations);
    }
}
