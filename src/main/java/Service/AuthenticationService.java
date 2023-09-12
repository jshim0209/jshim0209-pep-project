package Service;

import DTO.LoginDTO;
import Model.Account;

public interface AuthenticationService {
    public Account loginAccount(LoginDTO LoginDTO);
}
