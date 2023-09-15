package Service.Impl;

import java.sql.SQLException;

import DAO.AccountRepository;
import Model.Account;
import Service.AuthenticationService;
import io.javalin.http.UnauthorizedResponse;

public class AuthenticationServiceImpl implements AuthenticationService {

    private AccountRepository accountRepository;

    public AuthenticationServiceImpl() {
        this.accountRepository = new AccountRepository();
    }

    public AuthenticationServiceImpl(AccountRepository mockAccountRepository) {
        this.accountRepository = mockAccountRepository;
    }

    @Override
    public Account loginAccount(String username, String password) throws SQLException {
        Account loggedInAccount = accountRepository.getUserByUsernameAndPassword(username, password);
        if (loggedInAccount == null) {
            throw new UnauthorizedResponse("Invalid username/password provided!");
        }
        return loggedInAccount;
    }
    
}
