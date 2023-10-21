package com.chalenges.userservice.dao.impl;

import com.chalenges.userservice.dao.UserSessionDaoService;
import com.chalenges.userservice.models.UserSession;
import com.chalenges.userservice.repository.UserSessionRepository;
import org.springframework.stereotype.Service;
/**
 * @author lady Cuizara
 */
@Service
public class UserSessionDaoServiceImpl implements UserSessionDaoService {
    private UserSessionRepository userSessionRepository;

    public UserSessionDaoServiceImpl(UserSessionRepository userSessionRepository) {
        this.userSessionRepository = userSessionRepository;
    }

    @Override
    public UserSession save(UserSession userSession) {
        return userSessionRepository.save(userSession);
    }
}
