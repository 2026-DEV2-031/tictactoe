package katas.tictactoe.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import katas.tictactoe.Symbols
import katas.tictactoe.TicTacToeState

@Composable
fun TicTacToeScreen(
    state: TicTacToeState,
    onMove: (Int) -> Unit,
    onReset: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        GameStatus(state)

        Spacer(modifier = Modifier.height(16.dp))

        Board(
            board = state.board,
            onCellClick = onMove
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (state is TicTacToeState.GameOver) {
            Button(onClick = onReset) {
                Text("Play again")
            }
        }
    }
}

@Composable
fun GameStatus(state: TicTacToeState) {
    val text = when (state) {
        is TicTacToeState.Initial ->
            "Player ${state.currentPlayer} starts"

        is TicTacToeState.InProgress ->
            "Player ${state.currentPlayer}'s turn"

        is TicTacToeState.GameOver ->
            state.winner?.let { "Player $it wins!" } ?: "It's a draw"
    }

    Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall
    )
}

@Composable
fun Board(
    board: List<Symbols?>,
    onCellClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.size(300.dp)
    ) {
        items(board.size) { index ->
            Cell(
                symbol = board[index],
                onClick = { onCellClick(index) }
            )
        }
    }
}

@Composable
fun Cell(
    symbol: Symbols?,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .border(1.dp, Color.Black)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = symbol?.name ?: "",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}