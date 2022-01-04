package com.mailCampaign.ws.campaign;

import com.mailCampaign.ws.contact.Contact;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SentCampaignInfoService {

    private SentCampaignInfoRepository repo;

    public SentCampaignInfoService(SentCampaignInfoRepository sentCampaignInfoRepository){
        this.repo = sentCampaignInfoRepository;
    }

    public List<SentCampaignInfo> createSentCampaignInfo(CampaignMail mail){
        int sentCampaignCount = mail.getSelectedContactList().size();

        List<SentCampaignInfo> infoList = new ArrayList<>();
        SentCampaignInfo newItem;
        for(int i=0; i<sentCampaignCount; i++){
            newItem = new SentCampaignInfo();
            Contact contact = mail.getSelectedContactList().get(i);
            newItem.setToSentMail(contact.getEmail());
            newItem.setToSentName(contact.getName());

            newItem.setCampaignName(mail.getHeader());

            String uniqueCode = createHash(contact.getEmail(),mail.getHeader());
            newItem.setUniqueCode(uniqueCode);

            newItem.setToSentTime(new Date(System.currentTimeMillis()));
            newItem.setUrl("http://localhost:8080/campaignlink/" + uniqueCode);

            infoList.add(newItem);
        }
        return infoList;
    }

    public String createHash(String mail, String header) {
        return String.valueOf(Math.abs((17 + mail.hashCode())*37 + header.hashCode()));
    }

    public void saveAllSentCampaignInfo(List<SentCampaignInfo> infoList){
        repo.saveAll(infoList);
    }

    public List<SentCampaignInfo> findAll() {
        return repo.findAll();
    }

    public SentCampaignInfo findByUniqueCode(String code) {
        return repo.findByUniqueCode(code);
    }

    public void update(SentCampaignInfo info){
        repo.save(info);
    }
}
