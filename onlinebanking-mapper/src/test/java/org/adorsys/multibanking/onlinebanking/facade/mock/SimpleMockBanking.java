package org.adorsys.multibanking.onlinebanking.facade.mock;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.adorsys.multibanking.mock.domain.BankAccessData;
import de.adorsys.multibanking.mock.domain.BankAccountData;
import de.adorsys.multibanking.mock.domain.MockAccount;
import de.adorsys.multibanking.mock.domain.XLSBank;
import de.adorsys.multibanking.mock.loader.*;
import de.adorsys.onlinebanking.mock.MockBanking;
import domain.Bank;
import domain.BankAccount;
import domain.Booking;
import domain.StandingOrder;
import domain.request.LoadAccountInformationRequest;
import domain.request.LoadBalanceRequest;
import domain.request.LoadBookingsRequest;
import domain.response.LoadAccountInformationResponse;
import domain.response.LoadBookingsResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Mock Banking operating on the base of a json file.
 *
 * @author fpo
 */
public class SimpleMockBanking extends MockBanking {

    private ObjectMapper mapper = new ObjectMapper();
    private MockAccount data = new MockAccount();

    public SimpleMockBanking(InputStream banksStream, InputStream bookingsStream) {
        try {
            load(banksStream, bookingsStream);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public LoadAccountInformationResponse loadBankAccounts(String bankingUrl, LoadAccountInformationRequest loadAccountInformationRequest) {
        List<BankAccount> bankAccounts = loadBalances(null,
                LoadBalanceRequest.builder()
                .bankApiUser(null)
                .bankAccess(loadAccountInformationRequest.getBankAccess())
                .bankCode(null)
                .pin(loadAccountInformationRequest.getPin())
                .build());
        return LoadAccountInformationResponse.builder()
                .bankAccounts(bankAccounts)
                .build();
    }

    @Override
    public LoadBookingsResponse loadBookings(String bankingUrl, LoadBookingsRequest loadBookingsRequest) {
        String bankLogin = loadBookingsRequest.getBankAccess().getBankLogin();
        String iban = loadBookingsRequest.getBankAccount().getIban();
        BankAccessData bankAccessData = data.accessOrException(bankLogin);
        bankAccessData.checkPin(loadBookingsRequest.getPin());
        BankAccountData accountData = data.accessOrException(bankLogin).accountDataOrException(iban);
        List<Booking> bookings = accountData.bookings();
        List<StandingOrder> standingOrders = new ArrayList<>(accountData.standingOrders().values());
        return LoadBookingsResponse.builder().bookings(bookings).standingOrders(standingOrders).build();
    }

    @Override
    public List<BankAccount> loadBalances(String bankingUrl, LoadBalanceRequest loadBalanceRequest) {
        return data.loadBankAccounts(loadBalanceRequest.getBankAccess(), loadBalanceRequest.getBankCode(), loadBalanceRequest.getPin());
    }

    private void load(InputStream banksStream, InputStream bookingsStream) throws IOException {
        if (banksStream == null)
            banksStream = SimpleMockBanking.class.getResourceAsStream("/mock_bank.json");

        List<? extends Bank> banks = mapper.readValue(banksStream, new TypeReference<List<XLSBank>>() {
        });

        BankAccesLoader bankAccesLoader = new BankAccesLoader(data);
        MockBankCatalogue bankCatalogue = new MockBankCatalogue();
        bankCatalogue.setBanks(banks);
        BankAccountLoader bankAccountLoader = new BankAccountLoader(data, bankCatalogue);
        BookingLoader bookingLoader = new BookingLoader(data);
        StandingOrderLoader standingOrderLoader = new StandingOrderLoader(data);
        DataSheetLoader dataSheetLoader = new DataSheetLoader(bankAccesLoader, bankAccountLoader, bookingLoader, standingOrderLoader);

        if (bookingsStream == null)
            bookingsStream = SimpleMockBanking.class.getResourceAsStream("/mock_bank.xls");

        dataSheetLoader.loadDataSheet(bookingsStream);
    }

}
