package Service.Impl;

import java.sql.SQLException;
import java.util.List;

import DAO.MessageRepository;
import Model.Message;
import Service.MessageService;

public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;

    public MessageServiceImpl() {
        this.messageRepository = new MessageRepository();
    }

    public MessageServiceImpl(MessageRepository mockMessageRepository) {
        this.messageRepository = mockMessageRepository;
    }

    @Override
    public List<Message> getAllMessages() throws SQLException {
        return this.messageRepository.getAllMessages();
    }
    @Override
    public Message getMessageById(int messageId) throws SQLException {
        Message message = this.messageRepository.getMessageById(messageId);
        System.out.println(message);
        return message;
    }
    @Override
    public List<Message> getMessagesByAccountId(int accountId) throws SQLException {
        return this.messageRepository.getMessagesByAccountId(accountId);
    }
    @Override
    public Message deleteMessage(int messageId) throws SQLException {
        Message messageTobeDeleted = getMessageById(messageId);
        if (this.messageRepository.deleteMessage(messageId) == true) {
            return messageTobeDeleted;
        }
        return null;
    }
    
}
