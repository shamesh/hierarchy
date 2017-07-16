package com.shamesh.hierarchy.controller;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.shamesh.hierarchy.controller.EmployeeHandler;
import com.shamesh.hierarchy.entity.Employee;
import com.shamesh.hierarchy.repository.EmployeeRepository;

import static org.mockito.Mockito.*;

public class EmployeeHandlerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBuildChart_OneCEO() {
		EmployeeHandler emphandler = new EmployeeHandler();
		Employee employee = new Employee(100, "John");
		Employee employee1 = new Employee(101, "Harry");
		Employee employee2 = new Employee(102, "Saly");
		employee.addSubOrdinates(employee1);
		employee1.addSubOrdinates(employee2);
		String output=ReflectionTestUtils.invokeMethod(emphandler, "buildChart", Collections.singleton(employee),0);
		String input="John\n\tHarry\n\t\tSaly\n";
		Assert.assertEquals(input,output);
	}
	
	@Test
	public void testBuildChart_AddEmployer_NoManager() {
		EmployeeHandler emphandler = new EmployeeHandler();
		EmployeeRepository empRepo = mock(EmployeeRepository.class);
		when(empRepo.findOne(100)).thenReturn(null);
		emphandler.setEmpRepo(empRepo);	
		
		String output=ReflectionTestUtils.invokeMethod(emphandler, "addEmployees", "John",110,100);
		System.out.println(output);
		String input="ManagerId 100 Not Found. Employee John Not Added / Updated";
		Assert.assertEquals(input,output);
	}
	
	@Test
	public void testBuildChart_AddEmployer_WithManager() {
		EmployeeHandler emphandler = new EmployeeHandler();
		EmployeeRepository empRepo = mock(EmployeeRepository.class);
		when(empRepo.findOne(100)).thenReturn(new Employee(100,"Harry"));
		emphandler.setEmpRepo(empRepo);	
		
		String output=ReflectionTestUtils.invokeMethod(emphandler, "addEmployees", "John",110,100);
		System.out.println(output);
		verify(empRepo).save(new Employee(110,"John"));
		verify(empRepo).save(new Employee(100,"Harry"));		
	}
	
	

}
