package email;

import variables.GlobalVariables;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

/**
 * Created by thuan on 04/01/2017.
 */
public class MyImap {
    private String host;
    private String email;
    private String password;

    public MyImap(String host, String email, String password) {
        this.host = host;
        this.email = email;
        this.password = password;
    }

    private Folder getInbox(Store store) throws MessagingException {
        store.connect(this.host, this.email, this.password);

        Folder myFolder = store.getFolder("INBOX");
        myFolder.open(Folder.READ_ONLY);

        return myFolder;
    }

    public Message getMessage(Date dateTerm) throws MessagingException {
        Store workingStore = getStore();
        Folder workingFolder = getInbox(workingStore);

        try {
            // TODO: 06/01/2017 have no idea why Folder.search() does not work !!! 
            Message[] messages = workingFolder.getMessages();
            for (Message msg : messages) {
                if (isValidSender(msg)
                        && msg.getSubject().equals(GlobalVariables.FORGOT_PWD_SUBJECT)
                        && msg.getReceivedDate().compareTo(dateTerm) > 0)
                    return msg;
            }
        } finally {
            workingFolder.close(false);
            workingStore.close();
        }

        throw new RuntimeException("Can't find the message with given info");
    }

    public Message getMessageWithRetry(Date dateTerm, int retryCount) throws
            MessagingException, InterruptedException {
        int count = 1;
        while (count <= retryCount) {
            try {
                count++;

                Message msg = getMessage(dateTerm);

                if (!Objects.isNull(msg)) {
                    System.out.println("Message has been found !!!");
                    System.out.println("Found email: " + msg.getSubject() + "---" + msg.getReceivedDate().toString());
                    return msg;
                }

            } catch (RuntimeException ex) {
                System.out.println("Waiting for message arrives ... 5s");
                Thread.sleep(5000);
            }
        }

        throw new RuntimeException("Can't find the message with given info after " + retryCount + " times");
    }

    private boolean isValidSender(Message message) throws MessagingException {
        return InternetAddress.toString(message.getFrom()).equals(GlobalVariables.SENDER);
    }

    private Store getStore() throws NoSuchProviderException {
        Session session = Session.getDefaultInstance(getConnectProperties());

        return session.getStore("imaps");
    }

    private Properties getConnectProperties() {
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");
        props.setProperty("mail.imaps.port", "993");
        props.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.imap.socketFactory.fallback", "false");
//        props.setProperty("mail.debug", "true");
        return props;
    }
}
