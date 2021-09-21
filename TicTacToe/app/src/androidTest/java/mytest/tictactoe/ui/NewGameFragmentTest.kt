package mytest.tictactoe.ui

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import mytest.tictactoe.R
import mytest.tictactoe.launchFragmentInHiltContainer
import mytest.tictactoe.ui.newgame.NewGameFragment
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Integration test for the NewGame screen.
 */
@HiltAndroidTest
class NewGameFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun testNavigationToInGameFragment() {
        // GIVEN - On the "Add Task" screen.
        val navController = TestNavHostController(getApplicationContext())
        launchFragment(navController)

        // WHEN - Valid playerX and playerO and click start
        onView(withId(R.id.player_x_autoCompleteTextView)).perform(replaceText("john"))
        onView(withId(R.id.player_o_autoCompleteTextView)).perform(replaceText("smith"))
        onView(withId(R.id.start_button)).perform(click())

        // THEN - Verify that we navigated to the InGame screen.
        assertEquals(navController.currentDestination?.id, R.id.inGameFragment)
    }

    private fun launchFragment(navController: TestNavHostController) {
        launchFragmentInHiltContainer<NewGameFragment>() {
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(R.id.newGameFragment)
            Navigation.setViewNavController(requireView(), navController)
        }
    }

}