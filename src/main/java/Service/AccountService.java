package Service;

import java.sql.SQLException;

import Exception.AccountAlreadyExistException;
import Model.Account;

public interface AccountService {

    public Account createNewAccount(Account accountToAdd) throws SQLException, AccountAlreadyExistException;
    public Account getAccountByUsername(String username) throws SQLException; 
    
}
