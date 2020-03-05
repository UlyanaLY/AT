package ru.stqa.at.mantis.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.at.mantis.model.UserData;

import java.util.List;

public class HbConnectionTest {
	private SessionFactory sessionFactory;

	@BeforeClass
	protected void setUp() throws Exception {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
						.configure() // configures settings from hibernate.cfg.xml
						.build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	@Test
	public void testHbConnection() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<UserData> result = session.createQuery("from UserData").list();
		for (UserData user : result) {
			System.out.println(user);
		}
		session.getTransaction().commit();
		session.close();
	}
}
