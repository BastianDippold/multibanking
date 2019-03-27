/*
 * Copyright 2018-2019 adorsys GmbH & Co KG
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

package de.adorsys.multibanking.domain.request;

import de.adorsys.multibanking.domain.AccountReference;
import de.adorsys.multibanking.domain.BankAccess;
import de.adorsys.multibanking.domain.BankApiUser;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class CreateConsentRequest {

    private BankApiUser bankApiUser;
    private BankAccess bankAccess;

    // Requested access services for a consent.
    /**
     * Is asking for detailed account information.
     */
    private List<AccountReference> accounts;
    /**
     * Is asking for balances of the account.
     */
    private List<AccountReference> balances;
    /**
     * Is asking for transactions of the account
     */
    private List<AccountReference> transactions;

    private boolean recurringIndicator;
    private LocalDate validUntil;
    private int frequencyPerDay;
    private boolean combinedServiceIndicator;
}