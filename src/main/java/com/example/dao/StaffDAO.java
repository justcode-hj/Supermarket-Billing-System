package com.example.dao;

import com.example.dto.Staff;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StaffDAO {

    @Autowired
    SessionFactory sessionFactory;

    public String addStaff(Staff staff) {
        Session session = null;
        Transaction transaction = null;
        String message = "";
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(staff);
            transaction.commit();
            message = "Staff added successfully";
        }
        catch (Exception e) {
            message = "Staff not added";
            transaction.rollback();
            System.out.println("Exception in addStaff() - StaffDAO : " + e.getMessage());
        }
        finally {
            session.close();
        }
        return message;
    }

    public String updateStaff(Staff staff) {
        Session session = null;
        Transaction transaction = null;
        String message = "";
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(staff);
            transaction.commit();
            message = "Staff updated successfully";
        } catch (Exception e) {
            message = "Staff not updated";
            transaction.rollback();
            System.out.println("Exception in updateStaff() - StaffDAO : " + e.getMessage());
        } finally {
            session.close();
        }
        return message;
    }

    public String deleteStaff(long id) {
        Session session = null;
        Transaction transaction = null;
        String message = "";
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Staff staff = (Staff) session.get(Staff.class, id);
            session.delete(staff);
            transaction.commit();
            message = "Staff deleted successfully";
        } catch (Exception e) {
            message = "Staff not deleted";
            transaction.rollback();
            System.out.println("Exception in deleteStaff() - StaffDAO : " + e.getMessage());
        } finally {
            session.close();
        }
        return message;
    }

    public List<Staff> getAllStaff() {
        Session session = null;
        List<Staff> staffList = null;
        try {
            session = sessionFactory.openSession();
            staffList = session.createQuery("from Staff").list();
        } catch (Exception e) {
            System.out.println("Exception in getAllStaff() - StaffDAO : " + e.getMessage());
        } finally {
            session.close();
        }
        return staffList;
    }

}
