package xyz.genzsage.genzsagebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.genzsage.genzsagebackend.model.Sage;

import java.util.Optional;

public interface SageRepository extends JpaRepository<Sage,String> {

    Optional<Sage> findByIdentity(String identity);
    Optional<Sage> findByIdentityOrEmailOrPhoneNumber(String identity, String email, String phoneNumber);

    Optional<Object> findByEmail(String email);
}
