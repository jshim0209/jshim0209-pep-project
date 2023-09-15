package Service;

import java.sql.SQLException;
import java.util.List;

import Model.Message;

public interface MessageService {

    public abstract List<Message> getAllMessages() throws SQLException;
    public abstract Message getMessageById(int messageId) throws SQLException;
    public abstract List<Message> getMessagesByAccountId(int accountId) throws SQLException;
    public abstract Message deleteMessage(int messageId) throws SQLException;
}
