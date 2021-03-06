package de.adorsys.multibanking.domain;

import de.adorsys.multibanking.encrypt.Encrypted;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by alexg on 05.09.17.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Document
@Encrypted(exclude = {"_id", "accountId", "userId"})
@CompoundIndexes({
        @CompoundIndex(name = "account_index", def = "{'userId': 1, 'accountId': 1}")
})
public class StandingOrderEntity extends StandingOrder {

    @Id
    private String id;
    private String accountId;
    private String userId;
    private Object tanSubmitExternal;

}
