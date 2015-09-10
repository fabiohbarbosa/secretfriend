package br.com.fabiohgbarbosa.secretfriend.web.rest.sortition;

import br.com.fabiohgbarbosa.secretfriend.exception.SecretFriendServiceException;
import br.com.fabiohgbarbosa.secretfriend.sortition.SortitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * ENDPOINT RESTful /sortition
 * Created by fabio on 08/09/15.
 */
@RestController
@RequestMapping("/sortition")
public class SortitionController {

    @Autowired
    private SortitionService service;

    /**
     * Sortition secret friends
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<SortitionDTO> get() {
        return SortitionDTOAdapter.from(service.execute());
    }

    /**
     * Send e-mail to users
     * @param sortitionDTOs Sortition with person and friend selected
     */
    @RequestMapping(value = "/send_email", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendEmail(@Valid @RequestBody final List<SortitionDTO> sortitionDTOs) {
        service.sendEmail(sortitionDTOs);
    }

}