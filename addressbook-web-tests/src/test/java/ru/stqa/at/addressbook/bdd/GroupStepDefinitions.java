package ru.stqa.at.addressbook.bdd;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.remote.BrowserType;
import ru.stqa.at.addressbook.appmanager.ApplicationManager;
import ru.stqa.at.addressbook.model.GroupData;
import ru.stqa.at.addressbook.model.Groups;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupStepDefinitions {
    private Groups groups;
    private GroupData newGroup;
    private ApplicationManager app;

    @Before
    public void init() throws IOException {
       app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
       app.init();
    }

    @After
    public void stop() {
        app.stop();
        app = null;
    }

    @Дано("^: a set of groups$")
    public void loadGroups(){
        groups = app.db().groups();
    }

    @Когда("^: I create a new group with name (.+), header (.+) and footer (.+)$")
    public void createGroup(String name, String header, String footer){
        newGroup = new GroupData().withName(name).withHeader(header).withFooter(footer);
        app.goTo().groupPage();
        app.group().create(newGroup);
    }

    @Тогда("^: the new set of group is equal to the old set with the added group$")
    public void verifyGroupCreated(){
        Groups newGroups = app.db().groups  ();
        assertThat(newGroups, equalTo(groups
                .withAdded(newGroup.withId(newGroups.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
