package com.cx.management.controlar;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cx.management.entity.Customer;
import com.cx.management.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerConrolar {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/allcustomer")
    public ResponseEntity<?> getCustomer() {

        Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Customer> userList = customerService.getUser();
		if (!userList.isEmpty()) {
			map.put("status", 1);
			map.put("data", userList);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Data is not found");
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}
        
    }

    @PostMapping("/save")
	public ResponseEntity<?> saveUser(@RequestBody Customer customer) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
        customerService.save(customer);
		map.put("status", 1);
		map.put("message", "Record is Saved Successfully!");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
        
	}

    @DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Customer customer = customerService.findById(id);
            customerService.delete(customer);
			map.put("status", 1);
			map.put("message", "Record is deleted successfully!");
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", "Data is not found");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

     @PutMapping("/update/{id}")
	public ResponseEntity<?> updateUserById(@PathVariable Integer id, @RequestBody Customer userDetail) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
            Customer user = customerService.findById(id);
			user.setFirstName(userDetail.getFirstName());
			user.setLastName(userDetail.getLastName());
			user.setBirthDate(userDetail.getBirthDate());
			user.setLocation(userDetail.getLocation());
            customerService.save(user);
			map.put("status", 1);
			map.put("Massage", "Data has been updated sucessfully");
			map.put("data", customerService.findById(id));
			

			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", "Data is not found");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

}
