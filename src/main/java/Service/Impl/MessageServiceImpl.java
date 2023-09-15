package Service.Impl;

import java.sql.SQLException;
import java.util.List;

import DAO.MessageRepository;
import Exception.InvalidInputException;
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
        return messageRepository.getMessageById(messageId);
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

    @Override
    public Message createMessage(Message messageToAdd) throws SQLException, InvalidInputException {
        // validateUsername(messageToAdd.getUsername());
        // validatePassword(messageToAdd.getPassword());
        Message newMessage = messageRepository.createNewMessage(messageToAdd);
        if (newMessage == null) {
            throw new InvalidInputException("Invalid Input!");
        }
        return newMessage;
    }
    
}
