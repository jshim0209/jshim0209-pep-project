package Service;

import java.sql.SQLException;
import java.util.List;

import Exception.InvalidInputException;
import Model.Message;

public interface MessageService {
    List<Message> getAllMessages() throws SQLException;
    Message getMessageById(int messageId) throws SQLException;
    List<Message> getMessagesByAccountId(int accountId) throws SQLException;
    Message deleteMessage(int messageId) throws SQLException;
    Message createMessage(Message messageToAdd) throws SQLException, InvalidInputException;
    Message updateMessage(int messageId, String message_text) throws SQLException;
}
