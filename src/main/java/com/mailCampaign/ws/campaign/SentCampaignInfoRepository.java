package com.mailCampaign.ws.campaign;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SentCampaignInfoRepository  extends JpaRepository<SentCampaignInfo, Long> {

    List<SentCampaignInfo> findAll();

    SentCampaignInfo findByUniqueCode(String code);

}
