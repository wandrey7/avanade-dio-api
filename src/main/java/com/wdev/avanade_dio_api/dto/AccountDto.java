package com.wdev.avanade_dio_api.dto;

import com.wdev.avanade_dio_api.model.Account;
import java.math.BigDecimal;

public record AccountDto(
        Long id,
        String number,
        String agency,
        BigDecimal balance,
        BigDecimal limit
) {
    public static AccountDto fromEntity(Account account) {
        if (account == null) return null;

        return new AccountDto(
                account.getId(),
                account.getNumber(),
                account.getAgency(),
                account.getBalance(),
                account.getLimit()
        );
    }

    public Account toEntity() {
        Account account = new Account();
        account.setId(this.id);
        account.setNumber(this.number);
        account.setAgency(this.agency);
        account.setBalance(this.balance);
        account.setLimit(this.limit);
        return account;
    }
}