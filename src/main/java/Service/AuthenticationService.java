package Service;

import java.sql.SQLException;

import DTO.AccountDTO;
import Model.Account;

public interface AuthenticationService {
    public abstract Account loginAccount(String username, String password) throws SQLException;
}
