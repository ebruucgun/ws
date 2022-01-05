package com.mailCampaign.ws.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class SentCampaignInfoController {

    @Autowired
    private SentCampaignInfoService service;

    @GetMapping("api/1.0/getallsentcampaigns")
    public List<SentCampaignInfo> findAll() {
        return service.findAll();
    }
    
}
