package com.llekox.applications.ToDoApp.repo;

import com.llekox.applications.ToDoApp.model.ItemToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepo extends JpaRepository<ItemToDo,Long> {

}
