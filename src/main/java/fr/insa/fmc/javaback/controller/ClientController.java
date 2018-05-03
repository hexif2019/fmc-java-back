package fr.insa.fmc.javaback.controller;

import fr.insa.fmc.javaback.entity.Client;
import fr.insa.fmc.javaback.entity.Residence;
import fr.insa.fmc.javaback.repository.ClientRepository;
import fr.insa.fmc.javaback.repository.ResidenceRepository;
import fr.insa.fmc.javaback.service.GenerationService;
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
    @RequestMapping(method=RequestMethod.DELETE, value="/client")
    public String deleteAllClient() {
        clientRepository.deleteAll();
        //Optional<Client> client = clientRepository.findById(id);
        //clientRepository.delete(client);
        return "";
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/client/{id}")
    public String deleteClientById(@PathVariable String id) {
        clientRepository.deleteById(id);
        //Optional<Client> client = clientRepository.findById(id);
        //clientRepository.delete(client);
        return "";
    }
    @RequestMapping(method=RequestMethod.GET, value="/client/{id}")
    public Optional<Client> findClientById(@PathVariable String id) {
        Optional<Client> client = clientRepository.findById(id);
        return client;
    }

    @RequestMapping(method=RequestMethod.POST,value="/api/register",consumes="application/json")
    public RegistrationResponseWrapper register(@RequestBody RegisterWrapper params) throws Exception {
        String mdp = params.getPassword();
        if(mdp.isEmpty()){
            throw new Exception("you must assign a password");
        }
        UserWrapper user = params.getUser();
        String token = GenerationService.GenerateToken();
        Client client = new Client();
        //client.setId(user.getId());
        client.setNom(user.getNom());
        client.setPrenom(user.getPrenom());
        if(clientRepository.findClientByEmail(user.getEmail())!=null){
            throw new Exception("Cet adresse email existe deja");
        }
        client.setEmail(user.getEmail());
        client.setResidence(user.getResidence().getId());
        client.setMdp(mdp);
        clientRepository.save(client);
        RegistrationResponseWrapper registerResponse = new RegistrationResponseWrapper();
        registerResponse.setToken(token);
        user.setId(client.getId());
        registerResponse.setUser(user);
        return registerResponse;
        //TODO:exception
    }

    @RequestMapping(method=RequestMethod.POST, value="/api/authenticateToken",consumes="application/json")
    public UserWrapper anthentificationToken(@RequestBody AuthentificationTokenWrapper params)throws Exception{
        String email = params.getEmail();
        String token = params.getToken();
        Client client = clientRepository.connectionQuery(email);
        if(client==null){
            throw new Exception("le client est introuvable");
        }
        UserWrapper user = new UserWrapper();
        user.setId(client.getId());
        user.setEmail(client.getEmail());
        user.setNom(client.getNom());
        user.setPrenom(client.getPrenom());
        Optional<Residence> residence = residenceRepository.findById(client.getResidence());

        if(!(residence.isPresent())){
            throw new Exception("there is no residence");
        }
        user.setResidence(new ResidenceWrapper(residenceRepository.findById(client.getResidence()).get()));
        return user;
    }

    @RequestMapping(method=RequestMethod.POST, value="/api/authenticate",consumes="application/json")
    public AuthentificationResponseWrapper connection(@RequestBody AuthentificationWrapper params)throws Exception{
        String email = params.getEmail();
        String mdp = params.getPassword();
        Client client = clientRepository.connectionQuery(email,mdp);
        AuthentificationResponseWrapper authResponse = new AuthentificationResponseWrapper();
        if(client == null) {
            throw new Exception("le client est introuvable");
        }
        String token = GenerationService.GenerateToken();
        authResponse.setToken(token);
        UserWrapper user = new UserWrapper();
        user.setId(client.getId());
        user.setEmail(client.getEmail());
        user.setNom(client.getNom());
        user.setPrenom(client.getPrenom());

        Optional<Residence> residence = residenceRepository.findById(client.getResidence());

        if(!(residence.isPresent())){
            throw new Exception("there is no residence");
        }

        user.setResidence(new ResidenceWrapper(residenceRepository.findById(client.getResidence()).get()));

        authResponse.setUser(user);
        return authResponse;

    }
}



