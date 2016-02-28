package datn.controller;

import datn.entity.*;
import datn.repository.*;
import datn.response.TeacherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private WaveOfMakingProjectRepository waveOfMakingProjectRepository;

    @Autowired
    private ListOfStudentsForEachWaveRepository listOfStudentsForEachWaveRepository;

    @Autowired
    private ListOfTeachersForEachWaveRepository listOfTeachersForEachWaveRepository;

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
        Student student = new Student();
        student.setId(UUID.randomUUID().toString());
        studentRepository.save(student);

        Teacher teacher = new Teacher();
        teacher.setId(UUID.randomUUID().toString());
        teacherRepository.save(teacher);

        WaveOfMakingProject waveOfMakingProject = new WaveOfMakingProject();
        waveOfMakingProject.setId(UUID.randomUUID().toString());
        waveOfMakingProjectRepository.save(waveOfMakingProject);

        ListOfStudentsForEachWave listOfStudentsForEachWave = new ListOfStudentsForEachWave();
        listOfStudentsForEachWave.setStudent(student);
        listOfStudentsForEachWave.setWaveOfMakingProject(waveOfMakingProject);
        listOfStudentsForEachWaveRepository.save(listOfStudentsForEachWave);

        ListOfTeachersForEachWave listOfTeachersForEachWave = new ListOfTeachersForEachWave();
        listOfTeachersForEachWave.setTeacher(teacher);
        listOfTeachersForEachWave.setWaveOfMakingProject(waveOfMakingProject);
        listOfTeachersForEachWaveRepository.save(listOfTeachersForEachWave);

        return "index";
    }

}
