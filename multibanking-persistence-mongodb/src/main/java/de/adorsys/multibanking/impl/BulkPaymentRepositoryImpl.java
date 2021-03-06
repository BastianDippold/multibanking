package de.adorsys.multibanking.impl;

import de.adorsys.multibanking.domain.BulkPaymentEntity;
import de.adorsys.multibanking.pers.spi.repository.BulkPaymentRepositoryIf;
import de.adorsys.multibanking.repository.BulkPaymentRepositoryMongodb;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Profile({"mongo", "fongo"})
@Service
public class BulkPaymentRepositoryImpl implements BulkPaymentRepositoryIf {

    private final BulkPaymentRepositoryMongodb paymentRepository;

    @Override
    public void save(BulkPaymentEntity target) {
        paymentRepository.save(target);
    }

    @Override
    public void delete(String id) {
        paymentRepository.deleteById(id);
    }
}
