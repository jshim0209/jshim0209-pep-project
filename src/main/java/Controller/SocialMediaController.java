package Controller;

import java.util.List;

import Exception.AccountAlreadyExistException;
import Exception.InvalidInputException;
import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.AuthenticationService;
import Service.MessageService;
import Service.Impl.AccountServiceImpl;
import Service.Impl.AuthenticationServiceImpl;
import Service.Impl.MessageServiceImpl;
import io.javalin.Javalin;
import io.javalin.http.ExceptionHandler;
import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {

    private AccountService accountService;
    private AuthenticationService authenticationService;
    private MessageService messageService;

    public SocialMediaController() {
        this.accountService = new AccountServiceImpl();
        this.authenticationService = new AuthenticationServiceImpl();
        this.messageService = new MessageServiceImpl();
    }

    public SocialMediaController(AccountService mockAccountSService) {
        this.accountService = mockAccountSService;
    }

    public SocialMediaController(AuthenticationService mockAuthenticationService) {
        this.authenticationService = mockAuthenticationService;
    }

    public SocialMediaController(MessageService mockMessageService) {
        this.messageService = mockMessageService;
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();

        app.post("/register", registerAccount);
        app.post("/login", login);

        app.get("/messages", getAllMessages);
        app.get("/messages/{message_id}", getMessageById);
        app.get("/accounts/{account_id}/messages", getMessagesByAccountId);
        app.delete("/messages/{message_id}", deleteMessage);
        app.post("/messages", createMessage);

        app.exception(IllegalArgumentException.class, badArgument);
        app.exception(AccountAlreadyExistException.class, usernameTaken);
        app.exception(UnauthorizedResponse.class, invalidUsernameOrPassword);
        app.exception(InvalidInputException.class, invalidInput);

        return app;
    }

    private final Handler registerAccount = (ctx) -> {
        Account accountToAdd = ctx.bodyAsClass(Account.class);
        Account newAccount = accountService.createNewAccount(accountToAdd);
        ctx.json(newAccount);
    };

    private final Handler login = (ctx) -> {
        Account credentials = ctx.bodyAsClass(Account.class);
        Account loggedInAccount = this.authenticationService.loginAccount(credentials.getUsername(), credentials.getPassword());
        ctx.json(loggedInAccount);        
    };

    private final Handler getAllMessages = (ctx) -> {
        List<Message> messages = this.messageService.getAllMessages();
        ctx.json(messages);
    };

    private final Handler getMessageById = (ctx) -> {
        String message_id = ctx.pathParam("message_id");
        int messageId = Integer.parseInt(message_id);
        Message message = messageService.getMessageById(messageId);
        if (message != null) {
            ctx.json(message);
        } else {
            ctx.json("");
        }        
    };

    private final Handler getMessagesByAccountId = (ctx) -> {
        String account_id = ctx.pathParam("account_id");
        int accountId = Integer.parseInt(account_id);
        List<Message> messages = messageService.getMessagesByAccountId(accountId);
        ctx.json(messages);
    };

    private final Handler createMessage = (ctx) -> {
        Message messageToAdd = ctx.bodyAsClass(Message.class);
        Message newMessage = messageService.createMessage(messageToAdd);
        ctx.json(newMessage);
    };

    private final Handler deleteMessage = (ctx) -> {
        String message_id = ctx.pathParam("message_id");
        int messageId = Integer.parseInt(message_id);
        Message deletedMessage = messageService.deleteMessage(messageId);
        if (deletedMessage != null) {
            ctx.json(deletedMessage);
        } else {
            ctx.json("");
        }        
    };

    private final ExceptionHandler<IllegalArgumentException> badArgument = (exception, ctx) -> {
        ctx.status(400);
    };

    private final ExceptionHandler<AccountAlreadyExistException> usernameTaken = (exception, ctx) -> {
        ctx.status(400);
    };

    private final ExceptionHandler<UnauthorizedResponse> invalidUsernameOrPassword = (exception, ctx) -> {
        ctx.status(401);
    };

    private final ExceptionHandler<InvalidInputException> invalidInput = (exception, ctx) -> {
        ctx.status(400);
    };

}