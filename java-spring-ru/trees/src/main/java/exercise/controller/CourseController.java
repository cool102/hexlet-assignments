package exercise.controller;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(path = "")
    public Iterable<Course> getCorses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    // BEGIN
    @GetMapping(path = "/{id}/previous/")
    public List<Course> getParents(@PathVariable long id) {

        //получить текущий курс
        Course child = courseRepository.findById(id);
        //получить значение поля path текущего курса
        String childPath = child.getPath();
        if (childPath != null) {
            // собрать id вышестоящих курсов в список
            List<String> parentsId = Arrays.stream(childPath.split("\\.")).toList();
            // идти по списку и получать курс из БАЗЫ и ложить в список курсов
            List<Course> result = new ArrayList<>();
            for (String cur : parentsId) {
                Course course = courseRepository.findById(Long.parseLong(cur));
                result.add(course);
            }
            return result;
            // вернуть этот список
        } else {
            return Collections.emptyList();
        }

    }
    // END

}
