package com.mailCampaign.ws.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class CampaignMailController {

    @Autowired
    private CampaignMailService campaignMailService;

    @Autowired
    private SentCampaignInfoService sentCampaignInfoService;

    @PostMapping("/sendmail")
    public void createMail(@RequestBody CampaignMail mail){
        campaignMailService.sendMail(mail);
    }


    @RequestMapping(value ="/campaignlink/{extension:[0-9]+}" , method = RequestMethod.GET)
    public void handle(@PathVariable String extension) {
        String hash = extension;
        SentCampaignInfo info = sentCampaignInfoService.findByUniqueCode(hash);
        Long currentMillis = System.currentTimeMillis();
        Long timeToClick = currentMillis - info.getToSentTime().getTime();
        info.setTimeToClick(new Date(timeToClick));
        info.setTimeToReceive(new Date(currentMillis));
        sentCampaignInfoService.update(info);
    }
}



