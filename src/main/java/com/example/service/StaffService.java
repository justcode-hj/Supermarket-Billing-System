package com.example.service;

import com.example.dao.StaffDAO;
import com.example.dto.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    @Autowired
    StaffDAO staffDAO;

    public String addStaff(Staff staff)
    {
        return staffDAO.addStaff(staff);
    }

    public String updateStaff(Staff staff)
    {
        return staffDAO.updateStaff(staff);
    }

    public String deleteStaff(long id)
    {
        return staffDAO.deleteStaff(id);
    }

    public List<Staff> getAllStaff()
    {
        return staffDAO.getAllStaff();
    }

}
