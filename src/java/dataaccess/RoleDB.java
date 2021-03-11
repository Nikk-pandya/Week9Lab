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
import models.Role;

/**
 *
 * @author 468181
 */
public class RoleDB {
    public List<Role> getAll(int role) throws Exception
    {
        List<Role> roles = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM role WHERE role=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, role);
            rs = ps.executeQuery();
            while (rs.next()) {
                int roleId = rs.getInt(1);
                String userRole = rs.getString(2);
                Role allRoles = new Role(roleId, userRole);
                roles.add(allRoles);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        return roles;
    }
    
    public String getRole (int role) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT role_name FROM role WHERE role=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, role);
            rs = ps.executeQuery();

            return rs.getString(2);
            
        } catch (SQLException ex) {
            Logger.getLogger(RoleDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        return null;
    }
}
