package net.javaguides.banking_app.Mapper;

import net.javaguides.banking_app.dto.AccountDto;
import net.javaguides.banking_app.entity.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto){
        Account account=new Account(accountDto.getId(),accountDto.getAccountHolderName(),accountDto.getBalance());// accountdto->account
        return account;
    }

    public static AccountDto mapToAccountDto(Account account){
        AccountDto accountDto=new AccountDto(account.getId(),account.getAccountHolderName(),account.getBalance());
        return accountDto;
    }
}
