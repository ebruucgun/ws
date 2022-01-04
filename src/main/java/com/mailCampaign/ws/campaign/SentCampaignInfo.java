package com.mailCampaign.ws.campaign;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class SentCampaignInfo {

    @Id
    @GeneratedValue
    private  Long id;

    private String toSentMail;

    private String toSentName;

    private Date toSentTime;

    private Date timeToReceive;

    private String campaignName;

    private String url;

    private Long timeToClick;

    private String uniqueCode;

}
