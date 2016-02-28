package datn.controller;

import datn.entity.ListOfStudentsForEachWave;
import datn.entity.Officer;
import datn.entity.Student;
import datn.entity.WaveOfMakingProject;
import datn.repository.ListOfStudentsForEachWaveRepository;
import datn.repository.OfficerRepository;
import datn.repository.StudentRepository;
import datn.repository.WaveOfMakingProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    private WaveOfMakingProjectRepository waveOfMakingProjectRepository;

    @Autowired
    private ListOfStudentsForEachWaveRepository listOfStudentsForEachWaveRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPageS(HttpServletRequest request){
        return "index";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String loginPages(HttpServletRequest request){
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginPageas(HttpServletRequest request){
        Student obj = new Student();
        obj.setId(UUID.randomUUID().toString());
        studentRepository.save(obj);

        WaveOfMakingProject waveOfMakingProject = new WaveOfMakingProject();
        waveOfMakingProject.setId(UUID.randomUUID().toString());
        waveOfMakingProjectRepository.save(waveOfMakingProject);

        ListOfStudentsForEachWave listOfStudentsForEachWave = new ListOfStudentsForEachWave();
        listOfStudentsForEachWave.setStudent(obj);
        listOfStudentsForEachWave.setWaveOfMakingProject(waveOfMakingProject);
        listOfStudentsForEachWaveRepository.save(listOfStudentsForEachWave);


        return "index";
    }

}
