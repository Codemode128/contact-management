package com.shivam.cms.controller;

import com.shivam.cms.dto.ContactDTO;
import com.shivam.cms.service.ContactService;
import com.shivam.cms.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cms")
public class ContactController {

    @Autowired
    ContactService contactService;

    @Autowired
    RestTemplate restTemplate;

    @Value("${auth0.audience}")
    private String audience;

    @Value("${clientid}")
    private String client_id;

    @Value("${clientsecret}")
    private String client_secret;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUrl;

    @GetMapping(value = "/authenticate",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> login(){
        HttpHeaders headers = new HttpHeaders();
       // headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> map= new HashMap<>();
        map.put("client_id", client_id);
        map.put("client_secret",client_secret);
        map.put("audience",audience);
        map.put("grant_type","client_credentials");
        HttpEntity<Map<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                issuerUrl+"/oauth/token",  HttpMethod.POST, request , String.class);
        return new ResponseEntity<>(response.getBody(),HttpStatus.OK);
    }
    @GetMapping("/contacts")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<?> getContacts(){
        List<ContactDTO> contacts = contactService.findAllContacts();
        if(contacts.size()==0)
            return new ResponseEntity<>(new Response("No contacts are present in system","Success"),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(contacts,HttpStatus.OK);
    }

    @GetMapping("/contacts/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<ContactDTO> getContact(@PathVariable Integer id){
        ContactDTO contact = null;
        contact = contactService.findContact(id);
        return new ResponseEntity<>(contact,HttpStatus.OK);
    }

    @GetMapping("/contacts/email")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<List<ContactDTO>> getContactByEmail(@RequestParam String email){
        List<ContactDTO> contacts = contactService.findContactByEmail(email);
        return new ResponseEntity<>(contacts,HttpStatus.OK);
    }
    @GetMapping("/contacts/firstName")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<List<ContactDTO>> getContactByFirstName(@RequestParam String firstName){
        List<ContactDTO> contacts = contactService.findContactByFirstName(firstName);
        return new ResponseEntity<>(contacts,HttpStatus.OK);
    }

    @GetMapping("/contacts/lastName")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<List<ContactDTO>> getContactByLastName(@RequestParam String lastName){
        List<ContactDTO> contacts = contactService.findContactByLastName(lastName);
        return new ResponseEntity<>(contacts,HttpStatus.OK);
    }

    @PostMapping("/add")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<ContactDTO> addContact(@Valid @RequestBody ContactDTO contactDto){
        ContactDTO contact = contactService.addContact(contactDto);
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<?> updateContact(@PathVariable Integer id, @RequestBody ContactDTO contactDTO){
        ContactDTO contact = contactService.updateContact(contactDTO,id);
        return new ResponseEntity<>(contact,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<?> deleteContact(@PathVariable Integer id){
        ContactDTO contact = contactService.deleteContact(id);
        return new ResponseEntity<>(contact,HttpStatus.OK);
    }
}
