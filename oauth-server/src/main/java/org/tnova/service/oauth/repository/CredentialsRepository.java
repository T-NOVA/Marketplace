package org.tnova.service.oauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tnova.service.oauth.domain.Credentials;

public interface CredentialsRepository extends JpaRepository<Credentials, Long>
{

    Credentials findByName(String name);

}
