package com.mailCampaign.ws.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin
public class CampaignMailController {

    @Autowired
    private CampaignMailService campaignMailService;

    @Autowired
    private SentCampaignInfoService sentCampaignInfoService;

    @PostMapping("api/1.0/sendmail")
    public void createMail(@RequestBody CampaignMail mail){
        campaignMailService.sendMail(mail);
    }


    @RequestMapping(value ="/campaignlink/{extension:[0-9]+}" , method = RequestMethod.GET)
    public void handle(@PathVariable String extension) {
        String hash = extension;
        SentCampaignInfo info = sentCampaignInfoService.findByUniqueCode(hash);
        Long currentMillis = System.currentTimeMillis();
        Long timeToClick = currentMillis - info.getToSentTime().getTime();
        info.setTimeToClick(timeToClick);
        info.setTimeToReceive(new Date(currentMillis));
        sentCampaignInfoService.update(info);
    }
}



