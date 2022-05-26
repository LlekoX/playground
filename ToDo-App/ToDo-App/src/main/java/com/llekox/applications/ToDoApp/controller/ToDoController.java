package com.llekox.applications.ToDoApp.controller;

import com.llekox.applications.ToDoApp.model.ItemToDo;
import com.llekox.applications.ToDoApp.repo.ToDoRepo;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/todo")
public class ToDoController {

    @Autowired
    private ToDoRepo todoRepo;

    @GetMapping
    public List<ItemToDo> findAll(){
        return todoRepo.findAll();
    }

    @PostMapping
    public ItemToDo save(@Validated @NotNull @RequestBody ItemToDo itemToDo){
        return todoRepo.save(itemToDo);
    }
    @PutMapping
    public ItemToDo update(@Validated @NotNull @RequestBody ItemToDo itemToDo){
        return todoRepo.save(itemToDo);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        todoRepo.deleteById(id);
    }
}
