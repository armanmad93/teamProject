package com.example.techthink.controller.category;

import com.example.techthink.controller.category.model.request.CategoryRequest;
import com.example.techthink.controller.category.model.response.CategoryResponse;
import com.example.techthink.facade.category.CategoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryFacade facade;

    @Autowired
    public CategoryController(CategoryFacade facade) {
        this.facade = facade;
    }

    @PostMapping(value = "/admin/createCategory")
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest request){
        ResponseEntity<CategoryResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.create(request));
        return body;
    }

    @GetMapping(value = "/all/category/{id}")
    public ResponseEntity<CategoryResponse> readById(@PathVariable Integer id){
        ResponseEntity<CategoryResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.readById(id));
        return body;
    }

    @GetMapping(value = "/all/category/getAll")
    public ResponseEntity<List<CategoryResponse>> readAll(){
        ResponseEntity<List<CategoryResponse>> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.readAll());
        return body;
    }

    @PutMapping(value = "/admin/category/update/{id}")
    public ResponseEntity<CategoryResponse> update(@RequestBody CategoryRequest request, @PathVariable Integer id){
        ResponseEntity<CategoryResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.update(id, request));
        return body;
    }

    @DeleteMapping(value = "/admin/category/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id){
        ResponseEntity<Boolean> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.delete(id));
        return body;
    }

}
