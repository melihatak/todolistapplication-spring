package com.todolistapplication.todo.Entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "users")

 public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String password;


    public User(){

    }

    public String getUsername(){
       return  this.username;
    }

   public String getEmail(){
      return  this.email;
   }

   public String getPassword(){
      return  this.password;
   }


   public User(String username , String password , String  email){
       this.username = username ;
       this.password = password;
       this.email = email;

    }

    // Getters and Setters ... (Omitted for brevity)
}