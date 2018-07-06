package com.todolistapplication.todo;

import com.todolistapplication.todo.DAO.MysqlUserDAO;
import com.todolistapplication.todo.Entities.User;
import com.todolistapplication.todo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class HomeController  {
    private static final String responseSuccess = "{\"code\":200,\"status\":true}";
    private static final  String responseFailed= "{\"code\":400,\"status\":false}";
    private static final String TOKEN = "8a5da52ed126447d359e70c05721a8aa";


    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

    MysqlUserDAO userTemplate =
            (MysqlUserDAO) context.getBean("userTemplate");
    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        return userTemplate.getAllUsers();
    }

    /**
     * Creates new user api url
     * @var String token
     * @var String username
     * @var String password
     * @var String email
     * @return Response
     * */
    @RequestMapping(value = "/api/createuser/{token}/{username}/{password}/{email}", method = GET)
    @ResponseBody
    public Response createUser(
            @PathVariable("token") String token, @PathVariable("username") String username , @PathVariable("password") String password , @PathVariable("email") String email) {
        if(!token.toString().equals(TOKEN)){
            return Response.error();
        }
        User newUser = new User(username,password,email);
        boolean status = userTemplate.createUser(newUser);
        if(status)
            return Response.success();
        else
            return Response.error();
    }


    /**
     * User login api
     * @var String token
     * @var String username
     * @var String password
     * @return Response
     * */
    @RequestMapping(value = "/api/loginuser/{token}/{username}/{password}", method = GET)
    @ResponseBody
    public Response loginUser(
            @PathVariable("token") String token, @PathVariable("username") String username , @PathVariable("password") String password ) {
        if(!token.toString().equals(TOKEN)){
            return Response.error();
        }
        User user = userTemplate.findByUsername(username);
        if(user != null && user.getPassword().toString().equals(password)){
            return  Response.success();
        }else{
            return  Response.error();
        }
    }
}

