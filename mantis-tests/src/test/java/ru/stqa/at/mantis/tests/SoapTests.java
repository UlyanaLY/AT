package ru.stqa.at.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.at.mantis.model.Issue;
import ru.stqa.at.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase {

	@Test
	public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
		Set<Project> projects = app.soap().getProjects();
		System.out.println(projects.size());
		for (Project project : projects) {
			System.out.println(project.getName());
		}
	}

	@Test
	public void testCreateIssue () throws RemoteException, ServiceException, MalformedURLException {
		Set<Project> projects = app.soap().getProjects();
    Issue issue = new Issue().withSummary("Test issue").withDescription("Test issue description")
						.withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    assertEquals(issue.getSummary(), created.getSummary());
	}

	@Test
	public void testToSkip () throws RemoteException, ServiceException, MalformedURLException {
		System.out.println("Test");
		skipIfNotFixed(3);
	}
}
