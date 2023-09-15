package Service;

import java.sql.SQLException;

import Model.Account;

public interface AuthenticationService {
    public abstract Account loginAccount(String username, String password) throws SQLException;
}
