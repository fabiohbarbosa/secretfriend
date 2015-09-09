package br.com.fabiohgbarbosa.secretfriend.people.repository;

import br.com.fabiohgbarbosa.secretfriend.people.domain.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data People Repository
 * Created by fabio on 08/09/15.
 */
public interface PeopleRepository extends JpaRepository<People, Long> {
}
