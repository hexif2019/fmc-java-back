package fr.insa.fmc.javaback.controller;

import fr.insa.fmc.javaback.entity.Client;
import fr.insa.fmc.javaback.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    @Autowired
    ClientRepository clientRepository;

    @RequestMapping(method= RequestMethod.GET,value="/clients")
    public Iterable<Client> contact(){
        return clientRepository.findAll();
    }
}
