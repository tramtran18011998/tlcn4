package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.CurrentUser;
import com.example.demo.security.UserPrincipal;
import com.example.demo.service.CustomerService;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerService customerService;


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/me")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) throws ResourceNotFoundException {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @DeleteMapping("/api/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        User user = userService.findByUserId(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userService.delete(user);
        return ResponseEntity.ok(user);
    }


    //////////////////POST
//    @PostMapping(value = "/api/user", produces = MediaType.IMAGE_PNG_VALUE)
//    public ResponseEntity<?> createUser(@Valid @ModelAttribute Employee employee, @Valid @ModelAttribute User user, @RequestParam("file") MultipartFile file) throws IOException {
//
//        user.setName(signUpRequest.getName());
//        user.setEmail(signUpRequest.getEmail());
//        user.setPassword(signUpRequest.getPassword());
//        user.setProvider(AuthProvider.local);
//
//        user.setCreatedDate(new Date());
//        long a=1;
//        user.setInstatus(a);
//
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
//                .orElseThrow(() -> new AppException("User Role not set."));
//
//        user.setRoles(Collections.singleton(userRole));
//        logger.info("role2: "+Collections.singleton(userRole));
//
//        User result = userService.save(user);
//
//        Customer customer = new Customer();
//        customer.setUser(user);
//        customerService.save(customer);
//
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentContextPath().path("/user/me")
//                .buildAndExpand(result.getId()).toUri();
//
//        return ResponseEntity.created(location)
//                .body(new ApiResponse(true, "User registered successfully@"));
//    }


    ////////////////////////////UPDATE- UPLOAD
    //update
    @RequestMapping(value = "/api/users/{id}", method = RequestMethod.PUT,produces = "application/json")
    public @ResponseBody
    ResponseEntity<User> updateUser(@PathVariable(value = "id") long id, @Valid @RequestBody User user) throws ResourceNotFoundException {
        User currentUser= userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));

        if(user.getInstatus()!=null){
            currentUser.setInstatus(user.getInstatus());
        }
        if(user.getPassword()!=null){
            currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        currentUser.setLastModifiedDate(new Date());

        if(user.getName()!=null){
            currentUser.setName(user.getName());
        }
        if(user.getEmail()!=null){
            currentUser.setEmail(user.getEmail());
        }
        if(user.getAddress()!=null){
            currentUser.setAddress(user.getAddress());
        }
        if(user.getPhoneNumber()!=null){
            currentUser.setPhoneNumber(user.getPhoneNumber());
        }

        logger.info(currentUser.getAddress());
        userRepository.save(currentUser);

        return ResponseEntity.ok(currentUser);

    }

    @RequestMapping(value = "/api/userreset/{email}/{pass}", method = RequestMethod.PUT,produces = "application/json")
    public @ResponseBody
    ResponseEntity<User> resetPassword(@PathVariable(value = "email") String email, @PathVariable(value = "pass") String pass) throws ResourceNotFoundException {
        User currentUser = userRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User not found"));

        logger.info(pass);
        logger.info(email);
        currentUser.setPassword(passwordEncoder.encode(pass));
        currentUser.setLastModifiedDate(new Date());

        userRepository.save(currentUser);

        return ResponseEntity.ok(currentUser);

    }

    //update image customer information
    @PutMapping("api/userimg/{id}")
    public ResponseEntity<User> upload(@RequestParam("file") MultipartFile file, @PathVariable(value = "id") long id) throws IOException, ResourceNotFoundException {
        User user= userService.findByUserId(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        user.setImageUrl(userService.storeAvatar(file,id));
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    //upload img employee
    @PutMapping("api/employeeimg/{id}")
    public ResponseEntity<User> uploadImgEmp(@RequestParam("file") MultipartFile file, @PathVariable(value = "id") long id) throws IOException, ResourceNotFoundException {
        User user= userService.findByUserId(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        user.setImageUrl(userService.storeAvatarE(file,id));
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    ///////////////////////SERVE IMAGE
    //serve image employee
    @RequestMapping(value = "employeeimg/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public  byte[] getImageEmployee(@PathVariable(value = "imageName") String imageName) throws IOException {

        File serverFile = new File("uploads/employees/" + imageName);

        return Files.readAllBytes(serverFile.toPath());
    }

    //serve image customer
    @RequestMapping(value = "customerimg/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public  byte[] getImageCustomer(@PathVariable(value = "imageName") String imageName) throws IOException {

        File serverFile = new File("uploads/customers/" + imageName);

        return Files.readAllBytes(serverFile.toPath());
    }

    ////////////get user by id
    @GetMapping("/api/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        User user=userService.findByUserId(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        return ResponseEntity.ok().body(user);
    }


    /////////////////////////////CUSTOMER HANDLING
    //get list user role USER (customer)
    @GetMapping("/api/customer")
    public List<User> getCustomer(){
        return userRepository.listCustomer();
    }


    @GetMapping("/api/customeruser/{user_id}")
    public long getSupplierById(@PathVariable(value = "user_id") long user_id) throws ResourceNotFoundException {

        long id = userRepository.getCustomerId(user_id);
        return id;
    }

    @GetMapping("/api/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Customer customer=customerService.findCustomerId(id).orElseThrow(()-> new ResourceNotFoundException("Customer not found"));
        return ResponseEntity.ok().body(customer);
    }

    @PutMapping("/api/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") long id, @Valid @RequestBody Customer customer) throws ResourceNotFoundException {
        Customer currentCustomer= customerService.findCustomerId(id).orElseThrow(()-> new ResourceNotFoundException("Customer not found"));

        //CustomerType customerType = new CustomerType();
        //logger.info("i: "+String.valueOf(currentEmployee));
        if(customer.getCustomerType()!=null){
            currentCustomer.setCustomerType(customer.getCustomerType());
        }

        if(customer.getDiscount()!=0){
            currentCustomer.setDiscount(customer.getDiscount());
        }

        customerService.save(currentCustomer);

        return ResponseEntity.ok(currentCustomer);

    }

    /////////////////////////EMPLOYEE HANDLING
    @GetMapping("/api/employeeuser/{user_id}")
    public long getEmployeeById(@PathVariable(value = "user_id") long user_id) throws ResourceNotFoundException {
        long id = userRepository.getEmployeeId(user_id);
        return id;
    }

}
