package fr.insa.fmc.javaback.controller;

import fr.insa.fmc.javaback.entity.Magasin;
import fr.insa.fmc.javaback.entity.Residence;
import fr.insa.fmc.javaback.repository.MagasinRepository;
import fr.insa.fmc.javaback.repository.ResidenceRepository;
import fr.insa.fmc.javaback.wrapper.ResidenceWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class ResidenceController {

    @Autowired
    ResidenceRepository residenceRepository;

    @Autowired
    MagasinRepository magasinRepository;

    @RequestMapping(method=RequestMethod.GET, value="/residence")
    public Iterable<Residence> findResidence() {
        return residenceRepository.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, value="/residence")
    public Residence saveResidence(@RequestBody Residence residence) {
        residenceRepository.save(residence);

        return residence;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/residence/{id}")
    public String deleteResidenceById(@PathVariable String id) {
        residenceRepository.deleteById(id);
        //Optional<residence> residence = residenceRepository.findById(id);
        //residenceRepository.delete(residence);
        return "";
    }
    @RequestMapping(method=RequestMethod.GET, value="/residence/{id}")
    public Optional<Residence> findResidenceById(@PathVariable String id) {
        Optional<Residence> residence = residenceRepository.findById(id);
        return residence;
    }

    @RequestMapping(method=RequestMethod.GET,value="/api/getMagasinsOfResidence/{residenceid}")
    public ArrayList<Magasin> findNearMagasinsByResidenceId(@PathVariable String id) {
        Optional<Residence> residenceOpt = residenceRepository.findById(id);
        Residence residence = residenceOpt.get();
        Set<String> residenceId = residence.getIdMagasins();
        ArrayList<Magasin> nearMagasins = new ArrayList<>();
        Iterator<Magasin> it = (Iterator<Magasin>) magasinRepository.findAll();
        it.forEachRemaining(nearMagasins::add);
        return nearMagasins;
    }

    @RequestMapping(method=RequestMethod.GET,value="/api/findResidenceFormCodePostal/{codePostal}")
    public ArrayList<ResidenceWrapper> findResidenceFormCodePostal(@PathVariable String codePostal) {

        List<Residence> residences = residenceRepository.findResidenceByCodePostal(codePostal);

        ArrayList<ResidenceWrapper> residenceWrapList = new ArrayList<ResidenceWrapper>();

        //TODO: si liste vide, exception?

        for(Residence resid: residences){
            residenceWrapList.add(new ResidenceWrapper(resid));
        }

        return residenceWrapList;

    }




}

