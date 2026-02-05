package katas.tictactoe

import androidx.activity.ComponentActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TicTacToeInstrumentedTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    fun playMove(pos: Int) {
        composeRule.mainClock.autoAdvance = false
        composeRule.onNodeWithTag("cell_$pos").performClick()
        composeRule.mainClock.advanceTimeBy(500)
        composeRule.waitForIdle()
    }

    @Test
    fun x_wins_top_row() {
        composeRule.setContent {
            MaterialTheme {
                TicTacToeHost()
            }
        }

        playMove(0) // X -> 0
        playMove(3) // O -> 3
        playMove(1) // X -> 1

        playMove(4) // O -> 4
        playMove(2) // X -> 2 (win)

        // Assert winner text
        composeRule
            .onNodeWithText("Player X wins!")
            .assertIsDisplayed()
    }
}
