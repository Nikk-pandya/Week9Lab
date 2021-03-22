/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import models.Role;

/**
 *
 * @author 468181
 */
public class RoleDB {
    
    public List<Role> getAll() throws Exception
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            return em.createNamedQuery("Role.findAll", Role.class).getResultList();
        } 
        finally {
            em.close();
        }
    }
    
   
    
    public String getRole (int roleID) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Role role  = em.find(Role.class, roleID);
            return role.getRoleName();
        } finally {
            em.close();
        }
    }
    
        public int getRole (String roleName) {
        int roleId = 0;
        switch (roleName.toLowerCase()) {
            case "system admin" : roleId = 1; break;
            case "regular user" : roleId = 2; break;
            case "company admin" : roleId = 3; break;
        }
        return roleId;
    }
        
    public Role getRoleObject (int roleId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            return em.find(Role.class, roleId);
        } finally {
            em.close();
        }
    }
    
 
    
    
}