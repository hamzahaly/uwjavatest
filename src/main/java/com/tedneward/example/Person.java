package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person>{
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private int count;
  private boolean propertyChangeFired = false;
  
  public Person() {
    this("", 0, 0.0d);
    count++;
  }

  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    count++;
  }

  public void setAge(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("Age must be positive");
    } else {
      age = n;
    }
  }

  public void setName(String s) {
    if (s == null) {
      throw new IllegalArgumentException("Name cannot be null");
    } else {
      name = s;
    }
  }

  public void setSalary(double n) {
    salary = n;
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
    ssn = value;
    
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

  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }
    if (!(o instanceof Person)) {
      return false;
    }
    Person person = (Person) o;
    // field comparison
    return Objects.equals(name, person.name) && Objects.equals(age, person.age);
    //return (o.getAge() == this.age) && (o.getName() == this.name);
  }
  
  @Override
  public String toString() {
    return "[Person name:" + this.name + " age:" + this.age + " salary:" + this.salary + "]";
  }

  public static ArrayList<Person> getNewardFamily() {
    ArrayList<Person> fam = new ArrayList<Person>();
    Person matthew = new Person("Matthew", 15, 0);
    Person michael = new Person("Michael", 22, 10000);
    Person ted = new Person("Ted", 41, 250000);
    Person charlotte = new Person("Charlotte", 43, 150000);
    fam.add(ted);
    fam.add(charlotte);
    fam.add(michael);
    fam.add(matthew);
    return fam;
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

  public static class AgeComparator implements Comparator<Person>{
    
    @Override
    public int compare(Person person, Person anotherPerson) {
      return Integer.compare(person.getAge(), anotherPerson.getAge());
    }
  }
}
