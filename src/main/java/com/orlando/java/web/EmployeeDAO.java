
package com.orlando.java.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author orlando
 * [www.github.com/orlandogonz]
 * [www.linkedin.com/in/orlandogonz]
 * [orlandogonz.dev@gmail.com]
 */

//DAO - Pretende modelar con objetos un modelo de datos
//Se utiliza para realizar operaciones de datos: inserción, modificación, eliminación
public class EmployeeDAO {
    
    public EmployeeDAO(){
        super ();
    }
    
    //ResultSet es un objeto que presenta un conjunto de resultados que se obtienen 
    //como consecuencia de ejecutar la sentencia SQL a través de la conexión
    protected Employee processRow (ResultSet rs) throws SQLException {
        

        Employee employee = new Employee ();

        employee.setId (rs.getInt ("id"));
        employee.setFirstName (rs.getString ("firstName"));
        employee.setLastName (rs.getString ("lastName"));
        employee.setTitle (rs.getString ("title"));
        employee.setDepartment (rs.getString ("department"));
        employee.setCity (rs.getString ("city"));
        employee.setOfficePhone (rs.getString ("officePhone"));
        employee.setCellPhone (rs.getString ("cellPhone"));
        employee.setEmail (rs.getString ("email"));
        employee.setPicture (rs.getString ("picture"));
        int managerId = rs.getInt ("managerId");

        if (managerId>0) {

            Employee manager = new Employee ();

            manager.setId (managerId);
            manager.setFirstName (rs.getString ("managerFirstName"));
            manager.setLastName (rs.getString ("managerLastName"));
            employee.setManager (manager);
        }

        employee.setReportCount (rs.getInt ("reportCount"));

        return employee;
    }
    
    protected Employee processSummaryRow (ResultSet rs) throws SQLException {

        Employee employee = new Employee ();

        employee.setId (rs.getInt ("id"));
        employee.setFirstName (rs.getString ("firstName"));
        employee.setLastName (rs.getString ("lastName"));
        employee.setTitle (rs.getString ("title"));
        employee.setPicture (rs.getString ("picture"));
        employee.setReportCount (rs.getInt ("reportCount"));

        return employee;
    }
     
    //Realizaremos consulta de datos
    public List<Employee> findAll () {

        List<Employee> list = new ArrayList<> ();
        Connection c = null;

    	String sql = "SELECT e.id, e.firstName, e.lastName, e.title, e.picture, count(r.id) reportCount FROM employee as e " +
			"LEFT JOIN employee r ON e.id = r.managerId " +
			"GROUP BY e.id " +
			"ORDER BY e.firstName, e.lastName";
        
        try {

            c = ConnectionHelper.getConnection ();          
            
            //Statement provee la infraestructura para ejecutar entencias SQL 
            //sobre una conexión con una base de datos.
            Statement s = c.createStatement ();
            
            //El metodo executeQuery() se utiliza para ejecutar una sentencia SQL 
            //y obtener el resultado correspondiente dentro de un objeto del tipo ResultSet.
            ResultSet rs = s.executeQuery (sql); 
                
            //Muestra los datos
            while (rs.next()) {

                list.add (processSummaryRow (rs));
            }

        } catch (SQLException e) {
            
            e.printStackTrace();
            throw new RuntimeException(e);

	} finally {
            ConnectionHelper.close (c);
	}

        return list;
    }
    
    public List<Employee> findByName (String name) {

        List<Employee> list = new ArrayList<> ();
        Connection c = null;

        String sql = "SELECT e.id, e.firstName, e.lastName, e.title, e.picture, count(r.id) reportCount FROM employee as e " +
			"LEFT JOIN employee r ON e.id = r.managerId " +
			"WHERE UPPER(CONCAT(e.firstName, ' ', e.lastName)) LIKE ? " +	
			"GROUP BY e.id " +
			"ORDER BY e.firstName, e.lastName";

        try {

            c = ConnectionHelper.getConnection ();
            PreparedStatement ps = c.prepareStatement (sql);
            ps.setString(1, "%" + name.toUpperCase () + "%");
            ResultSet rs = ps.executeQuery ();

            while (rs.next ()) {

                list.add(processSummaryRow (rs));
            }

        } catch (SQLException e) {

            e.printStackTrace();
            throw new RuntimeException (e);

        } finally {
            
            ConnectionHelper.close (c);
	}

        return list;
    }
    
    public List<Employee> findByManager (int managerId) {

    	List<Employee> list = new ArrayList<> ();
    	Connection c = null;

    	String sql = "SELECT e.id, e.firstName, e.lastName, e.title, e.picture, count(r.id) reportCount FROM employee as e " +
			"LEFT JOIN employee r ON e.id = r.managerId " +
			"WHERE e.managerId=? " +	
			"GROUP BY e.id " +
			"ORDER BY e.firstName, e.lastName";

    	try {

    		c = ConnectionHelper.getConnection ();
    		PreparedStatement ps = c.prepareStatement (sql);
    		ps.setInt(1, managerId);
    		ResultSet rs = ps.executeQuery ();

    		while (rs.next ()) {

    			list.add (processSummaryRow (rs));
    		}

    	} catch (SQLException e) {

    		e.printStackTrace ();
    		throw new RuntimeException (e);

    	} finally {

    		ConnectionHelper.close (c);
    	}

    	return list;
    }
    
    public Employee findById (int id) {

    	String sql = "SELECT e.id, e.firstName, e.lastName, e.managerId, e.title, e.department, e.city, e.officePhone, e.cellPhone, " + 
			"e.email, e.picture, m.firstName managerFirstName, m.lastName managerLastName, count(r.id) reportCount FROM employee as e " +
			"LEFT JOIN employee m ON e.managerId = m.id " + 
			"LEFT JOIN employee r ON e.id = r.managerId " +
			"WHERE e.id = ? " +
			"GROUP BY e.id";

    	Employee employee = null;
        Connection c = null;

        try {

            c = ConnectionHelper.getConnection ();
            PreparedStatement ps = c.prepareStatement (sql);
            ps.setInt (1, id);
            ResultSet rs = ps.executeQuery ();

            if (rs.next ()) {

                employee = processRow (rs);
            }

        } catch (Exception e) {

            e.printStackTrace ();
            throw new RuntimeException (e);

		} finally {

			ConnectionHelper.close (c);
		}

        return employee;
    }

    //Guardaremos datos
    public Employee save(Employee employee)
	{
		return employee.getId() > 0 ? update(employee) : create(employee);
	}    
    
    //Crearemos nuevos datos
    public Employee create (Employee employee) {

        Connection c = null;
        PreparedStatement ps;

        try {

            c = ConnectionHelper.getConnection ();
            ps = c.prepareStatement ("INSERT INTO employee (firstName, lastName, title, department, managerId, city, officePhone, cellPhone, email, picture) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                new String[] { "ID" });

            ps.setString (1, employee.getFirstName ());
            ps.setString (2, employee.getLastName( ));
            ps.setString (3, employee.getTitle ());
            ps.setString (4, employee.getDepartment ());
            ps.setInt (5, employee.getManager () == null ? 0 : employee.getManager ().getId ());
            ps.setString (6, employee.getCity ());
            ps.setString (7, employee.getOfficePhone ());
            ps.setString (8, employee.getCellPhone ());
            ps.setString (9, employee.getEmail ());
            ps.setString (10, employee.getPicture ());
            ps.executeUpdate ();

            ResultSet rs = ps.getGeneratedKeys ();
            rs.next ();

            int id = rs.getInt (1);
            employee.setId (id);

        } catch (Exception e) {

            e.printStackTrace ();
            throw new RuntimeException (e);

		} finally {

			ConnectionHelper.close (c);
		}

        return employee;
    }
    
    //Actualizaremos datos
    public Employee update (Employee employee) {

        Connection c = null;

        try {

            c = ConnectionHelper.getConnection ();
            PreparedStatement ps = c.prepareStatement ("UPDATE employee SET firstName=?, lastName=?, title=?, department=?, managerId=?, city=?, officePhone=?, cellPhone=?, email=?, picture=? WHERE id=?");

            ps.setString (1, employee.getFirstName ());
            ps.setString (2, employee.getLastName ());
            ps.setString (3, employee.getTitle ());
            ps.setString (4, employee.getDepartment ());
            ps.setInt (5, employee.getManager () == null ? 0 : employee.getManager ().getId ());           
            ps.setString (6, employee.getCity ());
            ps.setString (7, employee.getOfficePhone ());
            ps.setString (8, employee.getCellPhone ());
            ps.setString (9, employee.getEmail ());
            ps.setString (10, employee.getPicture ());
            ps.setInt (11, employee.getId ());

            ps.executeUpdate ();

        } catch (SQLException e) {

            e.printStackTrace ();
            throw new RuntimeException (e);

		} finally {

			ConnectionHelper.close (c);
		}

        return employee;
    }

    //Eliminaremos datos
    public boolean remove (Employee employee) {

        Connection c = null;

        try {

            c = ConnectionHelper.getConnection ();            
            PreparedStatement ps = c.prepareStatement ("DELETE FROM employee WHERE id=?");
            ps.setInt(1, employee.getId ());

            int count = ps.executeUpdate ();
            return count == 1;

        } catch (Exception e) {

            e.printStackTrace ();
            throw new RuntimeException (e);

		} finally {

            ConnectionHelper.close (c);
		}
    }
     
    
}
