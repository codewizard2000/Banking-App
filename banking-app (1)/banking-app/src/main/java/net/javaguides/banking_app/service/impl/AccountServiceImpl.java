package net.javaguides.banking_app.service.impl;


import net.javaguides.banking_app.Mapper.AccountMapper;
import net.javaguides.banking_app.dto.AccountDto;
import net.javaguides.banking_app.entity.Account;
import net.javaguides.banking_app.repository.AccountRepository;
import net.javaguides.banking_app.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service// automatically creates spring bean for the class
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;// injecting dependency

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;// constructor based dependecy injection
    }

    @Override
    public AccountDto createAccount(AccountDto account) {
        Account account1 = AccountMapper.mapToAccount(account);
        Account savedAccount = accountRepository.save(account1);//  coverts the accountdto to account thans saves in jpa and return accountdto
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account dont exist"));
        return AccountMapper.mapToAccountDto(account);// converts account to dto account store id coming from db
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account dont exist"));
        double total = account.getBalance()+amount;// if account exist than add the amount
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account dont exist"));
        if(account.getBalance()<amount){
            throw new RuntimeException("Insufficient amount");
        }
        double total = account.getBalance()-amount;// if account exist than remove the amount
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts=accountRepository.findAll();
        return accounts.stream().map((account)->AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account dont exist"));
        accountRepository.deleteById(id);
    }
}
