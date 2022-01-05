package com.mailCampaign.ws.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("api/1.0/uploadfile")
    public List<Contact> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String result = new String(file.getBytes());
        return contactService.createContactsWithFile(result);
    }

    @GetMapping("api/1.0/getallcontacts")
    public List<Contact> findAll() {
        return contactService.findAll();
    }

    @PostMapping("api/1.0/create/contact")
    public Contact createContact(@RequestBody Contact contact){
        return contactService.saveContact(contact);
    }

}
