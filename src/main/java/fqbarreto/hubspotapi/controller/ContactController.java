package fqbarreto.hubspotapi.controller;

import fqbarreto.hubspotapi.model.ContactRequestModel;
import fqbarreto.hubspotapi.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private final ContactService _contactService;

    public ContactController(ContactService contactService) {
        this._contactService = contactService;
    }

    @PostMapping("/create-contact")
    public ResponseEntity<String> createContact(@RequestHeader("Authorization") String accessToken, @RequestBody ContactRequestModel contact) {
        return _contactService.createContact(accessToken, contact);
    }
}
