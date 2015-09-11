package br.com.fabiohgbarbosa.secretfriend.person.repository;

import br.com.fabiohgbarbosa.secretfriend.person.domain.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Spring Data Person Repository
 * Created by fabio on 08/09/15.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
    Page<Person> findByNameIgnoreCaseOrEmailIgnoreCase(String name, String email, Pageable pageable);
    long countByNameIgnoreCaseOrEmailIgnoreCase(String name, String email);
}
