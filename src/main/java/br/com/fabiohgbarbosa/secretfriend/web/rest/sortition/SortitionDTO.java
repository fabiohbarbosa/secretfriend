package br.com.fabiohgbarbosa.secretfriend.web.rest.sortition;

import br.com.fabiohgbarbosa.secretfriend.person.domain.entity.Person;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Transfer object to view
 * Created by fabio on 09/09/15.
 */
public class SortitionDTO implements Serializable {
    @NotNull(message = "Pessoa não pode ser nulo")
    private Person person;

    @NotNull(message = "Amigo escolhido não pode ser nulo")
    private Person friendSelected;

    public SortitionDTO() {
    }

    public SortitionDTO(final Person person, final Person friendSelected) {
        this.person = person;
        this.friendSelected = friendSelected;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(final Person person) {
        this.person = person;
    }

    public Person getFriendSelected() {
        return friendSelected;
    }

    public void setFriendSelected(final Person friendSelected) {
        this.friendSelected = friendSelected;
    }
}