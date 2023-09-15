package Service;

import java.sql.SQLException;

import Exception.AccountAlreadyExistException;
import Model.Account;

public interface AccountService {

    public abstract Account createNewAccount(Account accountToAdd) throws SQLException, AccountAlreadyExistException;
    public abstract Account getAccountByUsername(String username) throws SQLException; 
    
}
