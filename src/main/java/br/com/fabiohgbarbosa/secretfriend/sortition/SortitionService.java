package br.com.fabiohgbarbosa.secretfriend.sortition;

import br.com.fabiohgbarbosa.secretfriend.exception.SecretFriendServiceException;
import br.com.fabiohgbarbosa.secretfriend.people.domain.entity.People;
import br.com.fabiohgbarbosa.secretfriend.people.service.PeopleService;
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
    private PeopleService peopleService;

    @Autowired
    private SmtpServer smtpServer;

    /**
     * Sortition people and friends
     * @return Sortition result
     */
    public Map<People, People> execute() {
        try {
            final List<People> allPeoples = peopleService.findAll();

            if (allPeoples.size() < 2) {
                throw new SecretFriendServiceException("Impossível sortear com apenas uma pessoa", HttpStatus.BAD_REQUEST);
            }

            Map<People, People> result = new HashMap<>();
            List<People> sortitionPeople = new ArrayList<>(allPeoples);

            for(People people : allPeoples) {
                People friendSelected = findRandomPeople(people.getId(), sortitionPeople);
                result.put(people, friendSelected);
                sortitionPeople.remove(friendSelected);
            }

            return result;
        } catch (Exception e) {
            throw new SecretFriendServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Find random people different of id, and remove selected people of list
     * @param id People ID
     * @param peoples List of peoples
     * @return Selected people
     */
    protected People findRandomPeople(final Long id, final List<People> peoples) {
        List<People> manipulatePeoples = new ArrayList<>();
        manipulatePeoples.addAll(peoples);

        Long randomPeopleId = id;
        Integer randomValue = null;

        Random random = new Random();
        while(Objects.equals(randomPeopleId, id)) {
            randomValue = random.nextInt(manipulatePeoples.size());
            randomPeopleId = manipulatePeoples.get(randomValue).getId();
        }

        People selectPeople = manipulatePeoples.get(randomValue);
        manipulatePeoples.remove(selectPeople);

        return selectPeople;
    }

    /**
     * Send e-mail to people and friend
     * @param sortitionDTOs Sortition list
     */
    public void sendEmail(List<SortitionDTO> sortitionDTOs) {
        try {
            smtpServer.startServer();
            for (SortitionDTO sortitionDTO : sortitionDTOs) {
                String subject = "Seu amigo secreto é...";
                String name = sortitionDTO.getPeople().getName();
                String friendName = sortitionDTO.getFriendselected().getName();
                String body = getEmailBody(name, friendName);

                smtpServer.sendEmail(sortitionDTO.getFriendselected().getEmail(), subject, body);
            }
        } catch (Exception e) {
            throw new SecretFriendServiceException("Não foi possível enviar e-mail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String getEmailBody(final String name, final String friendName) {
        return "Parabéns " + name + ",\n\n" + "Seu amigo secreto é: " + friendName;
    }

}
