package fr.insa.fmc.javaback.controller;

import fr.insa.fmc.javaback.entity.Client;
import fr.insa.fmc.javaback.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class ClientController {

    @Autowired
    IClientRepository clientRepository;

    @RequestMapping(method=RequestMethod.GET, value="/client")
    public Iterable<Client> findClient() {
        return clientRepository.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, value="/client")
    public Client saveClient(@RequestBody Client client) {
        clientRepository.save(client);

        return client;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/client/{id}")
    public String deleteClientById(@PathVariable Long id) {
        clientRepository.deleteById(id);
        //Optional<Client> client = clientRepository.findById(id);
        //clientRepository.delete(client);
        return "";
    }
    @RequestMapping(method=RequestMethod.GET, value="/client/{id}")
    public Optional<Client> findClientById(@PathVariable Long id) {
        Optional<Client> client = clientRepository.findById(id);
        return client;
    }
}



