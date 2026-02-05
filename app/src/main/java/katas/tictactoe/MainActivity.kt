package katas.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import katas.tictactoe.ui.TicTacToeScreen
import katas.tictactoe.ui.theme.TictactoeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TictactoeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)){
                        TicTacToeHost()
                    }
                }
            }
        }
    }
}


@Composable
fun TicTacToeHost() {
    var state by remember {
        mutableStateOf<TicTacToeState>(TicTacToeState.Initial)
    }

    TicTacToeScreen(
        state,
        onMove = { index ->
            val player = state.currentPlayer ?: return@TicTacToeScreen
            state = state.makeMove(index, player)
        },
        onReset = {
            state = TicTacToeState.Initial
        }
    )
}