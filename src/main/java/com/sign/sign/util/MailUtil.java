package com.sign.sign.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author GOD
 * 实现一个可以发送加密邮件的邮件工具类
 */
public class MailUtil implements Runnable {
    private final String email;//收件人邮箱
    private final int code;//激活码
    private static final String from = "Knowlesea@126.com";//发件人电子邮箱

    public MailUtil(String email, int code) {
        this.email = email;
        this.code = code;
    }

    private Properties setProperties() {
        Properties properties = System.getProperties();//获取系统属性
        //指定发送邮件的主机smtp.126.com(网易)
        properties.setProperty("mail.smtp.host", "smtp.126.com");//设置邮件服务器
        properties.setProperty("mail.smtp.auth", "true");//打开认证
        properties.setProperty("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.port", "465");
        return properties;
    }

    @Override
    public void run() {
        //create connection object <code>javax.mail.Session</code>
        //create email object javax.mail.Message
        //send an activation email
        Properties properties = setProperties();

        try {
            //QQ邮箱需要下面这段代码，163邮箱不需要
//            MailSSLSocketFactory sf = new MailSSLSocketFactory();
//            sf.setTrustAllHosts(true);
//            properties.put("mail.smtp.ssl.enable", "true");
//            properties.put("mail.smtp.ssl.socketFactory", sf);
            // 获取默认session对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, "lanyixing9921"); // 发件人邮箱账号、授权码
                }
            });
            //create message
            Message message = setMessage(session);
            //send mail,the send() throws SendFailedException:if the message could not be sent to some or any of the recipients.
            Transport.send(message);// throws MessagingException
            //if the code is executed here, the email has been sent successfully
        } catch (SendFailedException e) {
            System.out.println("邮件发送失败");
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * create and return a message
     *
     * @param session the session
     * @return the message
     * @throws MessagingException messaging Exception
     */
    private Message setMessage(Session session) throws MessagingException {
        // create mail object
        Message message = new MimeMessage(session);
        // set message sender
        message.setFrom(new InternetAddress(from));
        // set message recipient
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(from));
        // set message subject
        message.setSubject("账户电子邮件验证");
        // create message content
        message.setHeader("Content-Type", "VerificationCode");
        String content = "<h1>账户电子邮件验证</h1>\n" +
                "<h2>验证您星乐天气账户:</h2>\n" +
                "<h2>验证码:<strong>" + code + "</strong></h2>" +
                "<p>您需要向星乐天气提供一个验证过的的电子邮件地址来使用星乐天气的完整功能，以及在未来能够安全的找回您的账户。</p>\n" +
                "<p>感谢您协助我们确认您的账户的安全性</p>\n" +
                "<p>*如果您最近并未使用本电子邮件地址新建星乐天气账户，您可以放心的忽略此邮件。</p>";
        //set message content
        message.setContent(content, "text/html;charset=UTF-8");
        return message;
    }

//    public static void main(String[] args) {
//        edu.wf.utils.MailUtil mail = new edu.wf.utils.MailUtil("1152436080@qq.com", 1234);
//        mail.run();
//    }

}