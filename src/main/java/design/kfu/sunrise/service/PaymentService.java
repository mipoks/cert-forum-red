package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Club;

/**
 * @author Daniyar Zakiev
 */
public interface PaymentService {
    void makePayment(Account account, Club club);
}
