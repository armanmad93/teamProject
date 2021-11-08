package com.example.techthink.controller.course;

import com.example.techthink.controller.course.model.request.CourseRequest;
import com.example.techthink.controller.course.model.response.CourseResponse;
import com.example.techthink.facade.course.CourseFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    private final CourseFacade facade;

    @Autowired
    public CourseController(CourseFacade facade) {
        this.facade = facade;
    }

    @PostMapping(value = "/admin/createCourse")
    public ResponseEntity<CourseResponse> create(@RequestBody CourseRequest request){
        ResponseEntity<CourseResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.create(request));
        return body;
    }

    @GetMapping(value = "/all/course/{id}")
    public ResponseEntity<CourseResponse> readById(@PathVariable Integer id){
        ResponseEntity<CourseResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.readById(id));
        return body;
    }

    @GetMapping(value = "/all/course/getAll")
    public ResponseEntity<List<CourseResponse>> readAll(){
        ResponseEntity<List<CourseResponse>> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.readAll());
        return body;
    }

    @PutMapping(value = "/admin/update/course/{id}")
    public ResponseEntity<CourseResponse> update(@RequestBody CourseRequest request, @PathVariable Integer id){
        ResponseEntity<CourseResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.update(id, request));
        return body;
    }

    @DeleteMapping(value = "/admin/delete/course/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id){
        ResponseEntity<Boolean> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.delete(id));
        return body;
    }
}
