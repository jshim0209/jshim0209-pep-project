package Service.Impl;

import DAO.AccountRepository;
import DTO.LoginDTO;
import Model.Account;
import Service.AuthenticationService;

public class AuthenticationServiceImpl implements AuthenticationService {

    private AccountRepository accountRepository;

    public AuthenticationServiceImpl() {
        this.accountRepository = new AccountRepository();
    }

    public AuthenticationServiceImpl(AccountRepository mockAccountRepository) {
        this.accountRepository = mockAccountRepository;
    }

    @Override
    public Account loginAccount(LoginDTO LoginDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loginAccount'");
    }
    
}
