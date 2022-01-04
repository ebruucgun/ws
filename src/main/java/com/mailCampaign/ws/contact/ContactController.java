package com.mailCampaign.ws.contact;

import com.mailCampaign.ws.error.ApiError;
import com.mailCampaign.ws.user.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/uploadfile")
    public List<Contact> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String result = new String(file.getBytes());
        return contactService.createContactsWithFile(result);
    }

    @GetMapping("/getallcontacts")
    public List<Contact> findAll() {
        return contactService.findAll();
    }

    @PostMapping("/create/contact")
    public Contact createContact(@RequestBody Contact contact){
        return contactService.saveContact(contact);
    }

}
