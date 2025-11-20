package com.sxit.campuslink.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sxit.campuslink.model.Board
import com.sxit.campuslink.viewmodel.BoardViewModel

@Composable
fun BoardListScreen(
    viewModel: BoardViewModel,
    onBoardClick: (Board) -> Unit
) {
    val boards by viewModel.boards.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    // 首次进入拉一次数据
    LaunchedEffect(Unit) {
        viewModel.fetchBoards()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("校园匿名论坛") })
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {

            when {
                loading -> {
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                }
                error != null -> {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("错误：$error")
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { viewModel.fetchBoards() }) {
                            Text("重试")
                        }
                    }
                }
                else -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(boards) { board ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .clickable { onBoardClick(board) },
                                elevation = 4.dp
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(board.name, style = MaterialTheme.typography.h6)
                                    board.description?.let {
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(it, style = MaterialTheme.typography.body2)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}