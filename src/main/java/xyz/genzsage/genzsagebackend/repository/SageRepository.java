package xyz.genzsage.genzsagebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.genzsage.genzsagebackend.model.Sage;

import java.util.List;
import java.util.Optional;

public interface SageRepository extends JpaRepository<Sage, String> {

    Optional<Sage> findByIdentity(String identity);

    Optional<Sage> findByIdentityOrEmailOrPhoneNumber(String identity, String email, String phoneNumber);

    Optional<Sage> findByEmail(String email);

    @Query("SELECT s FROM Sage s LEFT JOIN FETCH s.preferredTags WHERE s.identity = :identity")
    Optional<Sage> findByIdentityWithTags(@Param("identity") String identity);

    @Query("SELECT s FROM Sage s JOIN s.preferredTags t WHERE t.tagName = :tagName")
    List<Sage> findAllByPreferredTagName(@Param("tagName") String tagName);
}
