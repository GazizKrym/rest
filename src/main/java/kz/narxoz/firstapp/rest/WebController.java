package kz.narxoz.firstapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import kz.narxoz.firstapp.rest.repository.Userrepository;

import java.util.List;

@Controller
public class WebController {
    
    @Autowired
    Userrepository repository;

    @GetMapping("/")
    public String showUserList(@RequestParam(name="email", required=false, defaultValue="") String email,
                               @RequestParam(name="name", required=false, defaultValue="") String name,
                               @RequestParam(name="surname", required=false, defaultValue="") String surname,
                               @RequestParam(name="firstName", required=false, defaultValue="") String firstName,
                               @RequestParam(name="lastName", required=false, defaultValue="") String lastName,
                               @RequestParam(name="id", required=false, defaultValue="") Long id,
                               @RequestParam(name="Id", required=false, defaultValue="") Long Id,
                               @RequestParam(name="task5", required=false, defaultValue="") String task5,
                               @RequestParam(name="task6", required=false, defaultValue="") String task6,
                               @RequestParam(name="task7", required=false, defaultValue="") String task7,
                               @RequestParam(name="task8", required=false, defaultValue="") String task8,
                               @RequestParam(name="task9", required=false, defaultValue="") String task9,
                               @RequestParam(name="task10", required=false, defaultValue="") String task10,

                               Model model) {
        List<User> users = repository.findAll();

        if(!email.isEmpty()){
            users = repository.findByEmailContainingOrderBySurname(email);
        }

        if(!surname.isEmpty()){
            users = repository.findBySurnameStartsWith(surname);
        }

        if(!firstName.isEmpty() && !lastName.isEmpty()){
            users = repository.findByNameAndSurname(firstName, lastName);
        }


        if(!name.isEmpty()){
            users = repository.findTop2ByNameStartsWith(name);
        }

        if(null != id) {
            users = repository.findGreaterId(id);
        }

        if(Id!= null){
            users = repository.findByIdOrderById(Id);
        }

        if(!task5.isEmpty()){
            users = repository.findLastInsertedId();
        }

        if(!task6.isEmpty()){
            users = repository.findUsersOrderByNameDesc();
        }

        if(!task7.isEmpty()){
            users = repository.findByEmailNotContaining(task7);
        }

        if(!task8.isEmpty()){
            users = repository.findByNameEqualsSurname();
        }
        if(!task9.isEmpty()){
            users = repository.findByEmailContains();
        }
        if(!task10.isEmpty()){

            users = repository.findDistinctByName();
        }


        model.addAttribute("users", users);
        return "index";
    }

    @PostMapping("/adduser")
    public String createUser(@ModelAttribute User user){
        addUser(user);
        return "redirect:/";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, User user) {
        updateUser(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user =repository.getById(id);
        model.addAttribute("user", user);
        return "update-user";
    }

    private void deleteById(long id) {
        repository.deleteById(id);
    }

    private  void addUser(User newUser) {
        repository.save(newUser);
    }

    private  void updateUser(User updateUser) {
        User oldUser = repository.getById(updateUser.getId());

        oldUser.setName(updateUser.getName());
        oldUser.setSurname(updateUser.getSurname());
        oldUser.setEmail(updateUser.getEmail());
        
        repository.save(oldUser);
    }
}
