package fr.insa.fmc.javaback.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import fr.insa.fmc.javaback.entity.Casier;
import fr.insa.fmc.javaback.entity.Magasin;
import fr.insa.fmc.javaback.entity.MagasinsCommande;
import fr.insa.fmc.javaback.entity.Residence;
import fr.insa.fmc.javaback.repository.MagasinRepository;
import fr.insa.fmc.javaback.repository.ResidenceRepository;
import fr.insa.fmc.javaback.wrapper.CasierDisponibilite;
import fr.insa.fmc.javaback.wrapper.MagasinWrapper;
import fr.insa.fmc.javaback.wrapper.ResidenceWrapper;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.*;


@RestController
public class ResidenceController {

    @Autowired
    ResidenceRepository residenceRepository;

    @Autowired
    MagasinRepository magasinRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/residence")
    public Iterable<Residence> findResidence() {
        return residenceRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/residence",consumes="application/json")
    public Residence saveResidence(@RequestBody Residence residence) {
        residenceRepository.save(residence);

        return residence;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/residence/{id}")
    public String deleteResidenceById(@PathVariable String id) {
        residenceRepository.deleteById(id);
        //Optional<residence> residence = residenceRepository.findById(id);
        //residenceRepository.delete(residence);
        return "";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/residence/{id}")
    public Optional<Residence> findResidenceById(@PathVariable String id) {
        Optional<Residence> residence = residenceRepository.findById(id);
        return residence;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/residence")
    public String deleteAllResidence() {
        residenceRepository.deleteAll();
        //Optional<Client> client = clientRepository.findById(id);
        //clientRepository.delete(client);
        return "";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/getMagasinsOfResidence/{id}")
    public ArrayList<MagasinWrapper> findNearMagasinsByResidenceId(@PathVariable String id) throws Exception {
        Optional<Residence> residenceOpt = residenceRepository.findById(id);
        Residence residence = new Residence();
        if (residenceOpt.isPresent()) {
            residence = residenceOpt.get();
        } else {
            throw new Exception("cannot find residence by id");
        }
        Set<String> magasinsId = residence.getIdMagasins();
        ArrayList<MagasinWrapper> nearMagasins = new ArrayList<MagasinWrapper>();
        for (String magId : magasinsId) {
            Optional<Magasin> magasinTempo = magasinRepository.findById(magId);
            if (magasinTempo.isPresent()) {
                nearMagasins.add(new MagasinWrapper(magasinTempo.get()));
            } else {
                throw new NullPointerException("Magasin de la residence introuvable");
            }
        }

        return nearMagasins;
    }

    @RequestMapping(method=RequestMethod.GET, value="/residence/google/{address}")
    public String findRealCoordinate(@PathVariable String address) throws Exception {
        String ret = "";
        if (address != null && !address.equals("")) {
            try {
                address = URLEncoder.encode(address, "UTF-8");
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
            String[]  arr = new String[2];
            arr[0] = address;
            arr[1] = "AIzaSyAOSCBik6Kq_JXhYEaAmRr238iXxVq_qc4";
            String url = MessageFormat.format("https://maps.googleapis.com/maps/api/geocode/json?address={0}&key={1}",arr);
            URL urlmy = null;

            try {
                urlmy = new URL(url);
                HttpURLConnection con = (HttpURLConnection) urlmy.openConnection();
                con.setFollowRedirects(true);
                con.setInstanceFollowRedirects(false);
                con.connect();
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                String s = "";
                StringBuffer sb = new StringBuffer("");
                while ((s = br.readLine()) != null) {
                    //System.out.println(s);
                    sb.append(s + "\r\n");
                }
                ret = "" + sb;
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
        return ret;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/api/findResidenceFormCodePostal/{codePostal}")
    public ArrayList<ResidenceWrapper> findResidenceFormCodePostal(@PathVariable String codePostal) {

        List<Residence> residences = residenceRepository.findResidenceByCodePostal(codePostal);

        ArrayList<ResidenceWrapper> residenceWrapList = new ArrayList<ResidenceWrapper>();

        //TODO: si liste vide, exception?

        for (Residence resid : residences) {
            residenceWrapList.add(new ResidenceWrapper(resid));
        }

        return residenceWrapList;

    }

    @RequestMapping(method = RequestMethod.GET, value = "/mock/addcasiertoresidence/{residenceId}")
    public String mockAddCasierToResidence(@PathVariable String residenceId) {
        Optional<Residence> residenceOpt = residenceRepository.findById(residenceId);
        Residence residence = residenceOpt.get();
        Map<String, Casier> casiers = residence.getCasiers();
        for(int i = casiers.size();i<12;i++){
            Casier casier = new Casier();
            LinkedList<CasierDisponibilite> casierDispo = new LinkedList<>();
            casierDispo.add(new CasierDisponibilite(new Date()));
            casier.setDisponibilites(casierDispo);
            casier.setId(String.valueOf(casiers.size()));
            casiers.put(casier.getId(), casier);
        }
        residence.setCasiers(casiers);
        residenceRepository.save(residence);
        return "ok";
    }

    @RequestMapping(method= RequestMethod.GET, value="/residence/{idResidence}/{idMagasin}/")
    public String linkMagasinResidence(@PathVariable String idMagasin, @PathVariable String idResidence){
        Optional <Residence> resid = residenceRepository.findById(idResidence);
        Residence residence;
        if(resid.isPresent()){
            residence = resid.get();
        } else {
            throw new NullPointerException("Magasin introuvable");
        }

        residence.addMagasin(idMagasin);

        residenceRepository.save(residence);

        return "ok";
    }

}

