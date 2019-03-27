/*
 * Copyright 2018-2018 adorsys GmbH & Co KG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.adorsys.xs2a.pis.sepa;

import de.adorsys.psd2.client.model.AccountReference;
import de.adorsys.psd2.client.model.Amount;
import de.adorsys.psd2.client.model.PaymentInitiationSctJson;
import de.adorsys.xs2a.pis.PaymentInitiationBodyBuilder;
import de.adorsys.multibanking.domain.AbstractScaTransaction;
import de.adorsys.multibanking.domain.SinglePayment;

public class SepaSinglePaymentInitiationBodyBuilder implements PaymentInitiationBodyBuilder<PaymentInitiationSctJson> {
    @Override
    public PaymentInitiationSctJson buildBody(AbstractScaTransaction transaction) {
        SinglePayment paymentBodyObj = (SinglePayment) transaction;
        PaymentInitiationSctJson paymentInitiation = new PaymentInitiationSctJson();
        AccountReference debtorAccountReference = new AccountReference();
        debtorAccountReference.setIban(paymentBodyObj.getDebtorBankAccount().getIban());

        AccountReference creditorAccountReference = new AccountReference();
        creditorAccountReference.setIban(paymentBodyObj.getReceiverIban());

        Amount amount = new Amount();
        amount.setAmount(paymentBodyObj.getAmount().toString());
        amount.setCurrency(paymentBodyObj.getCurrency());

        paymentInitiation.setDebtorAccount(debtorAccountReference);
        paymentInitiation.setCreditorAccount(creditorAccountReference);
        paymentInitiation.setInstructedAmount(amount);
        paymentInitiation.setCreditorName(paymentBodyObj.getReceiver());
        paymentInitiation.setRemittanceInformationUnstructured(paymentBodyObj.getPurpose());
        return paymentInitiation;
    }
}