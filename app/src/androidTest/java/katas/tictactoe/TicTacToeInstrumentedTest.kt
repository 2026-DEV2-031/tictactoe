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

    @Test
    fun play_a_draw() {
        composeRule.setContent {
            MaterialTheme {
                TicTacToeHost()
            }
        }

        val moves = listOf(
            0 to Symbols.X, 1 to Symbols.X, 2 to Symbols.O,
            3 to Symbols.O, 4 to Symbols.O, 5 to Symbols.X,
            6 to Symbols.X, 7 to Symbols.X, 8 to Symbols.O)

        playMove(0) // X -> 0
        playMove(2) // O -> 2
        playMove(1) // X -> 1

        playMove(3) // O -> 3
        playMove(5) // X -> 5
        playMove(4) // O -> 4

        playMove(6) // X -> 6
        playMove(8) // O -> 8
        playMove(7) // X -> 7

        // Assert draw text
        composeRule
            .onNodeWithText("It's a draw")
            .assertIsDisplayed()
    }
}
