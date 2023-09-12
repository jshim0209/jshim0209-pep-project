package Service.Impl;

import java.sql.SQLException;

import DAO.AccountRepository;
import DTO.RegistrationDTO;
import Exception.AccountAlreadyExistException;
import Model.Account;
import Service.AccountService;

public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl() {
        this.accountRepository = new AccountRepository();
    }

    public AccountServiceImpl(AccountRepository mockAccountRepository) {
        this.accountRepository = mockAccountRepository;
    }

    public void validateUsername(String username) {
        if (username.trim().isBlank()) {
            throw new IllegalArgumentException("Username input is invalid!");
        }
        
    }

    public void validatePassword(String password) {
        if (password.trim().isBlank() || password.length() < 4) {
            throw new IllegalArgumentException("Password input is invalid!");
        }
    }

    public void validateAccountExists(String username) throws SQLException, AccountAlreadyExistException {
		if (accountRepository.getAccountByUsername(username) != null) {
			throw new AccountAlreadyExistException(username);
		}
	}

    @Override
    public Account createNewAccount(Account accountToAdd) throws SQLException, AccountAlreadyExistException {
        validateUsername(accountToAdd.getUsername());
        validatePassword(accountToAdd.getPassword());
        // validateAccountExists(registrationDTO.getUsername());
        return accountRepository.createNewAccount(accountToAdd);
              
        
    }

    @Override
    public Account getAccountByUsername(String username) throws SQLException {
        return accountRepository.getAccountByUsername(username);
    }
    
}
