package mvc_reminder.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import mvc_reminder.navigation.NavigateTo;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.ensure.Ensure;
import mvc_reminder.pages.Actions;
import mvc_reminder.pages.AllElements;

import java.util.List;

public class StepDefinitions {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }


    @Given("{actor} has no reminders in his list")
    public void validate_no_reminders_present(Actor actor) {
        actor.attemptsTo(
                Ensure.that(AllElements.NEW_TODO_FIELD).isNotDisplayed()
        );
    }

    @Given("{actor} is on create reminder landing page")
    public void on_mvc_landing_page(Actor actor) {
        actor.wasAbleTo(NavigateTo.theMvcToDoLandingPage());
    }


    @And("{actor} should see a total of {int} reminders added to the list")
    public void should_see_total_reminder_item(Actor actor, int size) {
        actor.attemptsTo(
                Ensure.thatTheSetOf(AllElements.TODO_LIST)
                        .hasSize(size)
        );
    }


    @When("{actor} creates a reminder {string}")
    public void createReminder(Actor actor, String newItem) {
        actor.attemptsTo(
                Actions.newReminder(newItem)
        );
    }


    @When("{actor} creates following reminders")
    public void createNewReminder(Actor actor, List<String> reminders) {

        for (int i = 0; i < reminders.size(); i++) {
            actor.attemptsTo(
                    Actions.newReminder(reminders.get(i))
            );
        }
    }

    @Then("{actor} should see {string} added to the reminder list")
    public void should_see_new_todo_item(Actor actor, String todoItem) {
        actor.attemptsTo(
                Ensure.that(AllElements.TODO_LIST).hasText(todoItem)
        );
    }

    @When("{actor} strikes out {string}")
    public void strike_out_reminder(Actor actor, String reminder) {
        actor.attemptsTo(Click.on(AllElements.COMPLETE_TASK((reminder))));
    }

    @Then("{actor} sees the active count to be {string}")
    public void should_see_active_count(Actor actor, String count) {
        actor.attemptsTo(
                Ensure.that(AllElements.TODO_ACTIVE_COUNT).hasText(count)
        );
    }

}
