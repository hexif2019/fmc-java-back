package fr.insa.fmc.javaback.controller;

import fr.insa.fmc.javaback.Exception.SmartException;
import fr.insa.fmc.javaback.entity.Client;
import fr.insa.fmc.javaback.entity.Residence;
import fr.insa.fmc.javaback.repository.ClientRepository;
import fr.insa.fmc.javaback.repository.ResidenceRepository;
import fr.insa.fmc.javaback.wrapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ResidenceRepository residenceRepository;

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

    @RequestMapping(method=RequestMethod.POST,value="/api/register",consumes="application/json")
    public RegistrationResponseWrapper register(@RequestBody RegisterWrapper params){
        String mdp = params.getPassword();
        UserWrapper user = params.getUser();
        String token = "blabla";
        Client client = new Client();
        client.setId(user.getId());
        client.setNom(user.getNom());
        client.setPrenom(user.getPrenom());
        client.setEmail(user.getEmail());
        client.setResidence(user.getResidence().getId());
        client.setMdp(mdp);
        clientRepository.save(client);
        RegistrationResponseWrapper registerResponse = new RegistrationResponseWrapper();
        registerResponse.setToken(token);
        registerResponse.setUser(user);
        return registerResponse;
        //TODO:exception
    }

    @RequestMapping(method=RequestMethod.POST, value="/api/authenticate",consumes="application/json")
    public AuthentificationResponseWrapper connection(@RequestBody AuthentificationWrapper params)throws NullPointerException{
        String email = params.getEmail();
        String mdp = params.getPassword();
        Client client = clientRepository.connectionQuery(email,mdp);
        AuthentificationResponseWrapper authResponse = new AuthentificationResponseWrapper();
        if(client == null) {
            throw new NullPointerException();

        }
        String token = "je_suis_le_token";
        authResponse.setToken(token);
        UserWrapper user = new UserWrapper();
        user.setId(client.getId());
        user.setEmail(client.getEmail());
        user.setNom(client.getNom());
        user.setPrenom(client.getPrenom());
        ResidenceWrapper residenceWarp = new ResidenceWrapper();
        Optional<Residence> residence = residenceRepository.findById(client.getResidence());
        if(residence.isPresent()){
            residenceWarp.setId(residence.get().getId());
            residenceWarp.setAdresse(residence.get().getAdresse());
            residenceWarp.setCodePostal(residence.get().getCodePostal());
            residenceWarp.setVille(residence.get().getVille());
            user.setResidence(residenceWarp);
        }
        authResponse.setUser(user);
        return authResponse;

    }
}



