package com.mailCampaign.ws.contact;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ContactService {

    private ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository){
        this.contactRepository = contactRepository;

    }

    public List<Contact> createContactsWithFile(String upload){
        String lines[] = upload.split("\\r?\\n");
        List<Contact> contacts = new ArrayList<Contact>();
        for (String line : lines) {
            Contact contact = new Contact();

            String parts[] = line.split(" ");
            contact.setName(parts[0]);

            String surname = "";
            for(int i=1; i< parts.length-1; i++ ) {
                surname = new StringBuilder()
                        .append(surname)
                        .append(parts[i] + " ")
                        .toString();
            }
            contact.setSurname(surname);
            contact.setEmail(parts[parts.length-1].replaceFirst("<", "").replaceFirst(">",""));
            contacts.add(contact);
        }
        return saveContacts(contacts);
    }

    public Contact saveContact(Contact contact){
        return contactRepository.save(contact);
    }

    public List<Contact> saveContacts(List<Contact> contactList){
        List<Contact> contacts = new ArrayList<Contact>();
        for(Contact cont: contactList){
            contacts.add(contactRepository.save(cont));
        }
        return contacts;
    }

    public List<Contact> findAll(){
        return contactRepository.findAll();
    }
}
