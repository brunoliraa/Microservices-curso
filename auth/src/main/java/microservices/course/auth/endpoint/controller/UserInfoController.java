package microservices.course.auth.endpoint.controller;

import microservices.course.core.model.ApplicationUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("user")
//@Api(value = "Endpoints to manage User's information")
public class UserInfoController {
    @GetMapping(path = "info", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  //  @ApiOperation(value = "Will retrieve the information from the user available in the token", response = ApplicationUser.class)
    public ResponseEntity<ApplicationUser> getUserInfo(Principal principal) {
        ApplicationUser applicationUser = (ApplicationUser) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        return new ResponseEntity<>(applicationUser, HttpStatus.OK);
    }
}
