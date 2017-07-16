package com.shamesh.hierarchy.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Employee {

	protected Employee() {

	}

	public Employee(Integer id) {
		super();
		this.id = id;
	}

	public Employee(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Id
	private Integer id;

	public Integer getId() {
		return id;
	}

	private String name;

	public String getName() {
		return name;
	}

	@OneToMany(mappedBy = "manager", fetch = FetchType.EAGER)
	private Set<Employee> subOrdinates;

	private Integer manager;

	public Set<Employee> getSubOrdinates() {
		if (subOrdinates == null)
			subOrdinates = new LinkedHashSet<Employee>();
		return subOrdinates;
	}

	public void setSubOrdinates(Set<Employee> subOrdinates) {
		this.subOrdinates = subOrdinates;
	}

	public void addSubOrdinates(Employee subOrdinate) {
		Employee emp = subOrdinate;
		emp.setManager(this.getId());
		this.getSubOrdinates().add(emp);

	}

	public Integer getManager() {
		return manager;
	}

	public void setManager(Integer manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {

		String result = name + "\t\n ";
		if (!getSubOrdinates().isEmpty())
			result += subOrdinates;
		return result;
	}

	@Override
	public boolean equals(Object emp) {
		if (emp == null)
			return false;
		Employee in = ((Employee) emp);

		return ((in.id == null ? false : in.id.equals(this.id))
				&& (in.name == null ? false : in.name.equals(this.name)));
	}

	@Override
	public int hashCode() {
		return 31 * (this.id == null ? 0 : this.id) + 7 * (this.name == null ? 0 : this.name.hashCode())
				+ 11 * (this.manager == null ? 0 : this.manager.hashCode());
	}
}
