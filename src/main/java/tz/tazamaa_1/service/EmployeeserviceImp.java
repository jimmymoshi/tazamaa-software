package tz.tazamaa_1.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tz.tazamaa_1.Dao.EmployeeDao;
import tz.tazamaa_1.model.Employee;
 


@Service("Employeeservice")
@Component
@Transactional
public class EmployeeserviceImp implements Employeeservice {
 
	 @Autowired
	    private EmployeeDao dao;
	     
	    public Employee findById(int id) {
	        return dao.findById(id);
	    }
	 
	    public void saveEmployee(Employee employee) {
	        dao.saveEmployee(employee);
	    }
	 
	    /*
	     * Since the method is running with Transaction, No need to call hibernate update explicitly.
	     * Just fetch the entity from db and update it with proper values within transaction.
	     * It will be updated in db once transaction ends. 
	     */
	    public void updateEmployee(Employee employee) {
	        Employee entity = dao.findById(employee.getId());
	        if(entity!=null){
	            entity.setName(employee.getName());
	            entity.setJoiningDate(employee.getJoiningDate());
	            entity.setSalary(employee.getSalary());
	            entity.setSsn(employee.getSsn());
	        }
	    }
	 
	    public void deleteEmployeeBySsn(String ssn) {
	        dao.deleteEmployeeBySsn(ssn);
	    }
	     
	    public List<Employee> findAllEmployees() {
	        return dao.findAllEmployees();
	    }
	 
	    public Employee findEmployeeBySsn(String ssn) {
	        return dao.findEmployeeBySsn(ssn);
	    }
	 
	    public boolean isEmployeeSsnUnique(Integer id, String ssn) {
	        Employee employee = findEmployeeBySsn(ssn);
	        return ( employee == null || ((id != null) && (employee.getId() == id)));
	    }
	     
	}