package org.zerock.bj1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.bj1.service.TodoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todo/")
@Log4j2
public class TodoController {
    
    private final TodoService todoService;

       // list
       @GetMapping("list")
       public void list(){
           log.info("list");
       }
   
       @GetMapping("add")
       public void add(){
           log.info("add");
       }
   
       @PostMapping("add")
       public String addPost(){
           log.info("Add Post..............");
           return "redirect:/todo/list";
       }
       
       @GetMapping("read/{tno}")
       public String read(@PathVariable("tno") Long tno) {
   
           log.info("read");
   
           return "/todo/read";
       }
   
       @GetMapping("modify/{tno}")
       public String modify(@PathVariable("tno") Long tno) {
   
           log.info("read");
   
           return "/todo/modify";
       }
   
       @PostMapping("remove")
       public String removePost() {
           log.info("Remove Post..............");
           return "redirect:/todo/list";
       }
       
       @PostMapping("modify/{tno}")
       public String modifyPost(@PathVariable("tno") Long tno) {
           
           log.info("Modify Post..............");
           return "redirect:/todo/read/" + tno;
       }
       

}
