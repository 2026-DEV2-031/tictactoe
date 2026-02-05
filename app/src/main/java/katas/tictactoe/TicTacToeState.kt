package katas.tictactoe

import katas.tictactoe.TicTacToeState.Companion.DiagonalRows
import katas.tictactoe.TicTacToeState.Companion.HorizontalRows
import katas.tictactoe.TicTacToeState.Companion.VerticalRows
import kotlin.collections.List

enum class Symbols { X, O }

sealed class TicTacToeState(open val board: List<Symbols?>, open val currentPlayer: Symbols?) {
    companion object {
        val HorizontalRows = listOf(
            listOf(0, 1, 2),
            listOf(3, 4, 5),
            listOf(6, 7, 8)
        )

        val VerticalRows = listOf(
            listOf(0, 3, 6),
            listOf(1, 4, 7),
            listOf(2, 5, 8)
        )

        val DiagonalRows = listOf(
            listOf(0, 4, 8),
            listOf(2, 4, 6)
        )
    }
    object Initial : TicTacToeState(List(size = 9) { null }, Symbols.X)
    data class InProgress(override val board: List<Symbols?>, override val currentPlayer: Symbols) : TicTacToeState(board, currentPlayer)
    data class GameOver(override val board: List<Symbols?>, val winner: Symbols?) : TicTacToeState(board, null){
        val isDraw = winner == null
    }

    fun makeMove(pos: Int, symbol: Symbols): TicTacToeState {
        // this position has already been played
        if(board[pos] != null) return this

        val newBoard = board.toMutableList().apply { this[pos] = symbol }

        // players take turns
        val newPlayer = if(currentPlayer == Symbols.X) Symbols.O else Symbols.X

        return when(this){
            is Initial -> {
                InProgress(newBoard, newPlayer)
            }
            is InProgress -> {
                val winner = getWinner(newBoard)

                if(winner != null){
                    GameOver(newBoard, winner) // a win
                } else if(newBoard.none { it == null }) {
                    GameOver(newBoard, null) // a draw
                } else {
                    InProgress(newBoard, newPlayer)
                }
            }
            is GameOver -> {
                this
            }
        }
    }
}
fun getWinner(board: List<Symbols?>): Symbols? {
    fun getRowWinner(rows: List<List<Int>>): Symbols? {
        for(row in rows){
            val symbol = board[row[0]]

            // A row wins if all three positions have the same non-null symbol
            if (symbol != null && symbol == board[row[1]] && symbol == board[row[2]]) {
                return symbol
            }
        }
        return null
    }

    return getRowWinner(HorizontalRows)
        ?: getRowWinner(VerticalRows)
        ?: getRowWinner(DiagonalRows)
}

