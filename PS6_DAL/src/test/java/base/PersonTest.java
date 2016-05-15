package base;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

//import domain.ObjectProperty;
import domain.PersonDomainModel;
//import PersonDomainModel.java;
//import PersonDAL;
import javafx.beans.property.ObjectProperty;
import java.time.LocalDate;

public class PersonTest {
	
	private static PersonDomainModel testPerson = new PersonDomainModel();
	private static PersonDomainModel testPerson1 = new PersonDomainModel();
	private static String firstName;
	private static String lastName;
	private static String street;
	private static String street1;
	private static int postalCode;
	private static String city;
	private static LocalDate birthday;
	private static UUID personID;
	
	private String newFirstName;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testPerson.setFirstName(firstName);
		testPerson.setLastName(lastName);
		testPerson.setStreet(street);
		testPerson.setPostalCode(postalCode);
		testPerson.setCity(city);
		testPerson.setBirthday(birthday);
		testPerson.setPersonID(personID);
		testPerson1.setFirstName(firstName);
		testPerson1.setLastName(lastName);
		testPerson1.setStreet(street1);
		testPerson1.setPostalCode(postalCode);
		testPerson1.setCity(city);
		testPerson1.setBirthday(birthday);
		testPerson1.setPersonID(personID);
		}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		testPerson = null;
		testPerson1 = null;
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd() {
		PersonDAL.addPerson(testPerson);
	}
	
	@Test
	public void testAddPerson(){
		PersonDAL.addPerson(testPerson);
	}
	
	@Test 
	public void testGetPersons(){
		 ArrayList<PersonDomainModel> testList = PersonDAL.getPersons();
		assertTrue(testList.get(0) == testPerson);
	}
	
	@Test
	public void testGetPerson(){
		assertTrue(PersonDAL.getPerson(personID) == testPerson);
	}
	
	@Test
	public void testUpdatePerson(){
		PersonDAL.updatePerson(testPerson1);
		assertTrue(PersonDAL.getPerson(personID) == testPerson1);
	}
	
	@Test
	public void testDeletePerson(){
		PersonDAL.deletePerson(personID);
	}
	
}
