package com.mailCampaign.ws.campaign;

import com.mailCampaign.ws.contact.Contact;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class CampaignMail {

    @Id
    @GeneratedValue
    private  Long id;

    private String header;

    private String body;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Contact> selectedContactList;

}
