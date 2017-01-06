package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private int count;
  private boolean propertyChangeFired = false;
  
  public Person() {
    this("", 0, 0.0d);
  }

  public Person(String n, int a, double s) {
    this.name = n;
    this.age = a;
    this.salary = s;
    this.count++;
  }

  public void setAge(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("Age must be positive");
    } else {
      this.age = n;
    }
  }

  public void setName(String s) {
    if (s == null) {
      throw new IllegalArgumentException("Name cannot be null");
    } else {
      this.name = s;
    }
  }

  public void setSalary(double n) {
    this.salary = n;
  }

  public int getAge() {
    return age;
  }

  public String getName() {
    return name;
  } 

  public double getSalary() {
    return salary;
  }

  public void setSSN(String value) {
    String old = ssn;
    this.ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  public String tostring() {
    return "{{FIXME}}";
  }

  public static ArrayList<Person> getNewardFamily() {
      return null;
  }

  public int count() {
    return count;
  }

  @Override
  public int compareTo(Person anotherPerson) {
    return Double.compare(anotherPerson.getSalary(), this.salary);
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }

  public class AgeComparator implements Comparable<Person> {
    
    public int compareTo(Person anotherPerson) {
      return Integer.compare(anotherPerson.getAge(), age);
    }
  }
}
