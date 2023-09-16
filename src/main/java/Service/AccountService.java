package Service;

import java.sql.SQLException;

import Exception.AccountAlreadyExistException;
import Model.Account;

public interface AccountService {
    Account createNewAccount(Account accountToAdd) throws SQLException, AccountAlreadyExistException;    
}
