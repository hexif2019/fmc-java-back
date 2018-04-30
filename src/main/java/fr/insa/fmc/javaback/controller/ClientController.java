package fr.insa.fmc.javaback.controller;

import fr.insa.fmc.javaback.entity.Client;
import fr.insa.fmc.javaback.entity.Residence;
import fr.insa.fmc.javaback.repository.ClientRepository;
import fr.insa.fmc.javaback.repository.ResidenceRepository;
import fr.insa.fmc.javaback.wrapper.AuthentificationResponseWrapper;
import fr.insa.fmc.javaback.wrapper.AuthentificationWrapper;
import fr.insa.fmc.javaback.wrapper.ResidenceWrapper;
import fr.insa.fmc.javaback.wrapper.UserWrapper;
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

    @RequestMapping(method=RequestMethod.POST, value="/api/authenticate",consumes="application/json")
    public AuthentificationResponseWrapper connection(@RequestBody AuthentificationWrapper params) {
        String email = params.getEmail();
        String mdp = params.getPassword();
        Client client = clientRepository.connectionQuery(email,mdp);
        AuthentificationResponseWrapper authResponse = new AuthentificationResponseWrapper();
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
            authResponse.setUser(user);
            return authResponse;
        }else return null;
    }
}



