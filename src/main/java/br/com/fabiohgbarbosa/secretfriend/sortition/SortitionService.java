package br.com.fabiohgbarbosa.secretfriend.sortition;

import br.com.fabiohgbarbosa.secretfriend.exception.SecretFriendServiceException;
import br.com.fabiohgbarbosa.secretfriend.person.domain.entity.Person;
import br.com.fabiohgbarbosa.secretfriend.person.service.PersonService;
import br.com.fabiohgbarbosa.secretfriend.smtpserver.SmtpServer;
import br.com.fabiohgbarbosa.secretfriend.web.rest.sortition.SortitionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Sortition Secret Friends
 * Created by fabio on 09/09/15.
 */
@Service
public class SortitionService {

    @Autowired
    private PersonService personService;

    @Autowired
    private SmtpServer smtpServer;

    /**
     * Sortition person and friends
     * @return Sortition result
     */
    public Map<Person, Person> execute() {
        try {
            final List<Person> allPersons = personService.findAll();

            if (allPersons.size() < 2) {
                throw new SecretFriendServiceException("Impossível sortear com apenas uma pessoa", HttpStatus.BAD_REQUEST);
            }

            Map<Person, Person> result = new HashMap<>();
            List<Person> sortitionPerson = new ArrayList<>(allPersons);

            for(Person person : allPersons) {
                Person friendSelected = findRandomPeople(person.getId(), sortitionPerson);
                result.put(person, friendSelected);
                sortitionPerson.remove(friendSelected);
            }

            return result;
        } catch (Exception e) {
            throw new SecretFriendServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Find random person different of id, and remove selected person of list
     * @param id Person ID
     * @param persons List of persons
     * @return Selected person
     */
    protected Person findRandomPeople(final Long id, final List<Person> persons) {
        List<Person> manipulatePersons = new ArrayList<>();
        manipulatePersons.addAll(persons);

        Long randomPeopleId = id;
        Integer randomValue = null;

        Random random = new Random();
        while(Objects.equals(randomPeopleId, id)) {
            randomValue = random.nextInt(manipulatePersons.size());
            randomPeopleId = manipulatePersons.get(randomValue).getId();
        }

        Person selectPerson = manipulatePersons.get(randomValue);
        manipulatePersons.remove(selectPerson);

        return selectPerson;
    }

    /**
     * Send e-mail to person and friend
     * @param sortitionDTOs Sortition list
     */
    public void sendEmail(List<SortitionDTO> sortitionDTOs) {
        try {
            smtpServer.startServer();
            for (SortitionDTO sortitionDTO : sortitionDTOs) {
                String subject = "Seu amigo secreto é...";
                String name = sortitionDTO.getPerson().getName();
                String friendName = sortitionDTO.getFriendSelected().getName();
                String body = getEmailBody(name, friendName);

                smtpServer.sendEmail(sortitionDTO.getFriendSelected().getEmail(), subject, body);
            }
        } catch (Exception e) {
            throw new SecretFriendServiceException("Não foi possível enviar e-mail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String getEmailBody(final String name, final String friendName) {
        return "Parabéns " + name + ",\n\n" + "Seu amigo secreto é: " + friendName;
    }

}
