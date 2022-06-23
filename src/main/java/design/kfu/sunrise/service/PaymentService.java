package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Club;

public interface PaymentService {
    void makePayment(Account account, Club club);
}
