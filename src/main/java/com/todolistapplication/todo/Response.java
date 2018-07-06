package  com.todolistapplication.todo;

import java.io.Serializable;

class ResponseSuccess extends  Response implements Serializable{
    public  boolean success = true;
    public  int code = 200;
}

class ResponseError  extends Response implements Serializable{
    public boolean success = false;
    public int code = 400;
}

public class Response implements Serializable  {
       public Response(){}

       public static Response success(){
           return new ResponseSuccess();
       }

       public static Response error(){
           return new ResponseError();
       }
}
