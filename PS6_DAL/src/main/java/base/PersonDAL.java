package base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.PersonDomainModel;
import domain.StudentDomainModel;
import util.HibernateUtil;

public class PersonDAL {

	public static PersonDomainModel addPerson(PersonDomainModel per) { //chack againced studentDAL
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.save(per);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return per;
		//[] PS6 - please implement
	}

	public static ArrayList<PersonDomainModel> getPersons() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		//PersonDomainModel getPerson = null;
		ArrayList<PersonDomainModel> persons = new ArrayList<PersonDomainModel>();
		try{
			//Transaction tx = session.beginTransaction();//begins interaction
			
			List person = session.createQuery("FROM StudentDomainModel").list();
			for (Iterator<PersonDomainModel> iterator = persons.iterator(); iterator.hasNext();){//selects each person from the list
				PersonDomainModel getPerson = (PersonDomainModel) iterator.next();
				persons.add(getPerson);//adds to a new arraylist
			}
			tx.commit();
		} catch(HibernateException e){
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} catch(Exception e){
				e.printStackTrace();
		} finally{
				session.close();
		}
		//PS6 - please implement		
		return persons;//returns arraylist
	}

	public static PersonDomainModel getPerson(UUID perID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel foundPerson = null;
		try{
			tx = session.beginTransaction();
			//search for perID
			Query query = session.createQuery("from PersonDomainModel where studentId = :id");
			query.setParameter("id", perID.toString());
			//cast to a list for management, should have only one member
			List<?> oneList = query.list();
			foundPerson = (PersonDomainModel) oneList.get(0);
			//foundPerson = per;
			//commit so rollback can happen if needed
			tx.commit();
		} catch(HibernateException e){
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			session.close();
		}
		//PS6 - please implement		
		return foundPerson;
	}

	public static void deletePerson(UUID perID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			//I don't know why this works
			PersonDomainModel person = (PersonDomainModel) session.get(PersonDomainModel.class, perID);
			session.delete(person);
			//commit changes
			tx.commit();
		} catch(HibernateException e){
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		//PS6 - please implement
	}

	public static PersonDomainModel updatePerson(PersonDomainModel per) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			//update info
			session.update(per);
			//commit changes
			tx.commit();
		} catch(HibernateException e){
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			session.close();
		}
		//PS6 - please implement		
		return per;
	}
}