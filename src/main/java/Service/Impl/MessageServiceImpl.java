package Service.Impl;

import java.sql.SQLException;
import java.util.List;

import DAO.AccountRepository;
import DAO.MessageRepository;
import Exception.InvalidInputException;
import Model.Message;
import Service.MessageService;

public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;
    private AccountRepository accountRepository;

    public MessageServiceImpl() {
        this.messageRepository = new MessageRepository();
        this.accountRepository = new AccountRepository();
    }

    public MessageServiceImpl(MessageRepository mockMessageRepository) {
        this.messageRepository = mockMessageRepository;
    }

    public MessageServiceImpl(AccountRepository mockAccountRepository) {
        this.accountRepository = mockAccountRepository;
    }

    public void validateMessageInput(String message_text) {
        if (message_text.isBlank() || message_text.length() >= 255) {
            throw new IllegalArgumentException("Invalid input provided!");
        }
    }

    public void validateOwner(int account_id) throws SQLException {
        if (accountRepository.getUserById(account_id) == null) {
            throw new IllegalArgumentException("User doesn't exist!");
        }
    }

    private void validateIfMessageExists(int messageId) throws SQLException {
        if (messageRepository.getMessageById(messageId) == null) {
            throw new IllegalArgumentException("Message doesn't exist!");
        }
    }

    @Override
    public List<Message> getAllMessages() throws SQLException {
        return messageRepository.getAllMessages();
    }
    @Override
    public Message getMessageById(int messageId) throws SQLException {
        return messageRepository.getMessageById(messageId);
    }
    @Override
    public List<Message> getMessagesByAccountId(int accountId) throws SQLException {
        return messageRepository.getMessagesByAccountId(accountId);
    }
    @Override
    public Message deleteMessage(int messageId) throws SQLException {
        Message messageTobeDeleted = getMessageById(messageId);
        if (messageRepository.deleteMessage(messageId) == true) {
            return messageTobeDeleted;
        }
        return null;
    }

    @Override
    public Message createMessage(Message messageToAdd) throws SQLException, InvalidInputException {
        validateMessageInput(messageToAdd.getMessage_text());
        validateOwner(messageToAdd.getPosted_by());
        Message newMessage = messageRepository.createNewMessage(messageToAdd);
        if (newMessage == null) {
            throw new InvalidInputException("Invalid Input!");
        }
        return newMessage;
    }

    @Override
    public Message updateMessage(int messageId, String message_text) throws SQLException {
        validateMessageInput(message_text);
        validateIfMessageExists(messageId);
        if (messageRepository.updateMessage(messageId, message_text) == true) {
            return messageRepository.getMessageById(messageId);
        }
        return null;
    }
    
}
