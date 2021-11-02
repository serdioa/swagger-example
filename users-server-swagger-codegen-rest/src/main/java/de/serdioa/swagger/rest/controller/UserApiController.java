package de.serdioa.swagger.rest.controller;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.serdioa.swagger.users.model.User;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController extends de.serdioa.swagger.users.api.UsersApiController {

    // Thread-local proxy for the HTTP request being processed.
    private final HttpServletRequest request;
    
    public UserApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        super(objectMapper, request);
        
        this.request = request;
        
        System.out.println("!!! UserApiController::UserApiController");
    
//        this.printRequest();
    }
    
    @Override
    public ResponseEntity<List<String>> usersGet() {
        System.out.println("!!! UserApiController::usersGet()");
    
        this.printRequest();
        
        return super.usersGet();
    }    
    
    @Override
    public ResponseEntity<User> usersUserIdGet(@Min(1L)@Parameter(in = ParameterIn.PATH, description = "The ID of the user to return.", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("userId") Long userId) {
        System.out.println("!!! UserApiController::usersUserIdGet(userId=" + userId + ")");
    
        this.printRequest();
        
        return super.usersUserIdGet(userId);
    }    
    
    private void printRequest() {
        Map<String, String []> params = this.request.getParameterMap();
        System.out.println("!!!     no. of parameters: " + params.size());
        for (Map.Entry<String, String []> entry : params.entrySet()) {
            final String name = entry.getKey();
            final String [] values = entry.getValue();
            
            if (values == null || values.length == 0) {
                System.out.println("!!!     " + name + " : <no value>");
            } else {
                for (String val : values) {
                    System.out.println("!!!     " + name + " : " + val);
                }
            }
        }
    }
}
