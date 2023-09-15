package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DTO.AccountDTO;
import DTO.LoginDTO;
import Model.Account;
import Util.ConnectionUtil;

public class AccountRepository {

    public Account getAccountByUsername(String username) throws SQLException {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM account WHERE username = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int acount_id = resultSet.getInt("account_id");
                String usernameFound = resultSet.getString("username");
                String passwordFound = resultSet.getString("password");

                return new Account(acount_id, usernameFound, passwordFound);
            } else {
                return null;
            }
            
        }
    }

    public Account createNewAccount(Account accountToAdd) throws SQLException {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO account (username, password) VALUES (?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, accountToAdd.getUsername());
            preparedStatement.setString(2, accountToAdd.getPassword());

            int numberOfAccountCreated = preparedStatement.executeUpdate();

            if (numberOfAccountCreated != 1) {
                throw new SQLException("Opening a new account was unsuccessful");
            }

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.next();
            int generatedId = resultSet.getInt(1);

            return new Account(generatedId, accountToAdd.getUsername(), accountToAdd.getPassword());
        }
    }

    public Account getUserByUsernameAndPassword(String username, String password) throws SQLException {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM account as a WHERE a.username = ? AND a.password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String accountUsername = resultSet.getString("username");
                    String accountPassword = resultSet.getString("password");
                    return new Account(username, password);
                }          
            }
            return null;            
        }
        
    }
    
}
