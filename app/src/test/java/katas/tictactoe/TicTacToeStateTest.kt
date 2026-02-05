package katas.tictactoe

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.isTrue
import org.junit.Test

class TicTacToeStateTest {
    val state0: TicTacToeState = TicTacToeState.Initial

    @Test
    fun `X always goes first`() {
        assertThat(state0.currentPlayer).isEqualTo(Symbols.X)
    }

    @Test
    fun `Players cannot play on a played position`() {
        val state1 = state0.makeMove(0, Symbols.X)
        val state2 = state1.makeMove(0, Symbols.O)

        assertThat(state1).isEqualTo(state2)
    }

    @Test
    fun `If a player is able to draw three marks in a horizontal row, that player wins`(){
        val horizontalRows = listOf(listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8))

        checkGameWinner(horizontalRows, Symbols.X)
    }

    @Test
    fun `If a player is able to draw three marks in a vertical row, that player wins`(){
        val verticalRows = listOf(
            listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8)
        )

        checkGameWinner(verticalRows, Symbols.X)
    }

    @Test
    fun `If a player is able to draw three marks in a diagonal row, that player wins`(){
        val diagonalRows = listOf(listOf(0, 4, 8), listOf(2, 4, 6))

        checkGameWinner(diagonalRows, Symbols.O)
    }

    @Test
    fun `If all nine squares are filled and neither player has three in a row, the game is a draw`(){
        // A series of moves resulting in a draw
        val moves = listOf(
            0 to Symbols.X, 1 to Symbols.X, 2 to Symbols.O,
            3 to Symbols.O, 4 to Symbols.O, 5 to Symbols.X,
            6 to Symbols.X, 7 to Symbols.X, 8 to Symbols.O)

        var state = state0
        for((pos, player) in moves){
            state = state.makeMove(pos,player)
        }

        assertThat(state).isInstanceOf<TicTacToeState.GameOver>()
            .transform { it.isDraw }
            .isTrue()
    }

    // Check that the game is won for each combination of moves
    private fun checkGameWinner(rows: List<List<Int>>, player: Symbols){
        for(row in rows){
            var state = state0

            for(pos in row){
                state = state.makeMove(pos, player)
            }

            assertThat(state).isInstanceOf<TicTacToeState.GameOver>()
                .transform { it.winner }
                .isEqualTo(player)
        }
    }
}