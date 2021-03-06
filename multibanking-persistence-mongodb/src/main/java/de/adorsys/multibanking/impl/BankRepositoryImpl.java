package de.adorsys.multibanking.impl;

import de.adorsys.multibanking.domain.BankEntity;
import de.adorsys.multibanking.pers.spi.repository.BankRepositoryIf;
import de.adorsys.multibanking.repository.BankRepositoryMongodb;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Profile({"mongo", "fongo"})
@Service
public class BankRepositoryImpl implements BankRepositoryIf {

    private final BankRepositoryMongodb bankRepositoryMongodb;
    private final MongoTemplate mongoTemplate;

    @Override
    public Optional<String> findBankingUrl(String bankCode) {
        Query query = new Query(
                Criteria.where("bankCode").is(bankCode)
        );
        query.fields().include("bankingUrl");
        return Optional.ofNullable(mongoTemplate.findOne(query, BankEntity.class))
                .map(BankEntity::getBankingUrl);
    }

    @Override
    public Optional<BankEntity> findByBankCode(String blz) {
        return bankRepositoryMongodb.findByBankCode(blz);
    }

    @Override
    public void save(Iterable<BankEntity> bankEntities) {
        bankRepositoryMongodb.saveAll(bankEntities);
    }

    @Override
    public void deleteAll() {
        bankRepositoryMongodb.deleteAll();
    }

    @Override
    public void save(BankEntity bank) {
        bankRepositoryMongodb.save(bank);
    }

    @Override
    public List<BankEntity> search(String text) {
        Collection<String> terms = new HashSet<>((Arrays.asList(text.split(" "))));

        Criteria[] criterias = terms
                .stream()
                .map(s -> Criteria.where("searchIndex").regex(s.toLowerCase(), "iu"))
                .toArray(Criteria[]::new);

        return mongoTemplate.find(Query.query(new Criteria().andOperator(criterias)), BankEntity.class);
    }

}
