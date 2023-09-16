package Service;

import java.sql.SQLException;
import java.util.List;

import Exception.InvalidInputException;
import Model.Message;

public interface MessageService {

    public abstract List<Message> getAllMessages() throws SQLException;
    public abstract Message getMessageById(int messageId) throws SQLException;
    public abstract List<Message> getMessagesByAccountId(int accountId) throws SQLException;
    public abstract Message deleteMessage(int messageId) throws SQLException;
    public abstract Message createMessage(Message messageToAdd) throws SQLException, InvalidInputException;
    public abstract Message updateMessage(int messageId, String message_text) throws SQLException;
}
