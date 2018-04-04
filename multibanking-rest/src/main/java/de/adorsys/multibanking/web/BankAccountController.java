package de.adorsys.multibanking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.adorsys.multibanking.service.BookingService;
import de.adorsys.multibanking.web.common.BankAccountBasedController;
import domain.BankApi;

/**
 * @author alexg on 07.02.17.
 * @author fpo 2018-03-20 11:45
 */
@UserResource
@RestController
@RequestMapping(path = BankAccountController.BASE_PATH)
public class BankAccountController extends BankAccountBasedController {
	public static final String BASE_PATH = "/api/v1/bankaccesses/{accessId}/accounts"; 

    @Autowired
    private BookingService bookingService;

    @RequestMapping(path = "/{accountId}/sync", method = RequestMethod.PUT)
    public HttpEntity<Void> syncBookings(
            @PathVariable String accessId,
            @PathVariable String accountId,
            @RequestBody(required = false) String pin) {

    	checkBankAccountExists(accessId, accountId);
        checkSynch(accessId, accountId);

        BankApi bankApi=null;
		bookingService.syncBookings(accessId, accountId, bankApi, pin);

        return new ResponseEntity<>(userDataLocationHeader(),HttpStatus.NO_CONTENT);
    }
}
