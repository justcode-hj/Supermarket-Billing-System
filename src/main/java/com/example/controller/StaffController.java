package com.example.controller;


import com.example.dto.Staff;
import com.example.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping("/staff")
    public List<Staff> getAllStaff() {
        return staffService.getAllStaff();
    }

    @PostMapping("/staff")
    public String createStaff(@RequestBody Staff staff) {
        return staffService.addStaff(staff);
    }

    @DeleteMapping("/staff/{id}")
    public String deleteStaff(@PathVariable long id) {
        return staffService.deleteStaff(id);
    }

    @PatchMapping("/staff/{id}")
    public String updateStaff(@PathVariable long id, @RequestBody Staff staff) {
        staff.setId(id);
        return staffService.updateStaff( staff);
    }

}
