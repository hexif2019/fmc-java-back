package fr.insa.fmc.javaback.controller;

import fr.insa.fmc.javaback.entity.Coursier;
import fr.insa.fmc.javaback.repository.CoursierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CousierController {
    @Autowired
    CoursierRepository coursierRepository;

    @RequestMapping(method=RequestMethod.POST, value="/coursier")
    public Coursier saveCoursier(@RequestBody Coursier coursier) {
        coursierRepository.save(coursier);
        return coursier;
    }
}
