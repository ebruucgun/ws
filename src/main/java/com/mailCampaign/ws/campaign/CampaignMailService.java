package com.mailCampaign.ws.campaign;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

@Service
public class CampaignMailService {

    private MailRepository mailRepository;

    private SentCampaignInfoService sentCampaignInfoService;

    public CampaignMailService(MailRepository mailRepository, SentCampaignInfoService sentCampaignInfoService){
        this.mailRepository = mailRepository;
        this.sentCampaignInfoService = sentCampaignInfoService;
    }

    public void sendMail(CampaignMail mail){

        List<SentCampaignInfo> campaignInfoList = sentCampaignInfoService.createSentCampaignInfo(mail);

        // Getting system properties
        Properties props = System.getProperties();

        // Setting up mail server
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        String account = "pdemo7834@gmail.com";
        String password = "qaz13579";

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(account,password);
            }
        });

        for (SentCampaignInfo info: campaignInfoList) {
            String mail_body =
                    "Hey " + info.getToSentName() + ",\n" +
                            mail.getBody() + "\n\nThis is your follow up link: " + info.getUrl();

            Message message = prepareMessage(session, account, info.getToSentMail(), mail_body, mail.getHeader()) ;
            try {
                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();

            }
        }
        sentCampaignInfoService.saveAllSentCampaignInfo(campaignInfoList);

    }

    private Message prepareMessage(Session session, String fromAccount, String toAccount, String subject, String text){

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toAccount));
            message.setSubject(subject);
            message.setText(text);
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;

    }
    
}
