package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.model.Authority;
import design.kfu.sunrise.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Authority addAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }
}
