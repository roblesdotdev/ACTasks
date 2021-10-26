package com.roblesdotdev.act.taskList.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.roblesdotdev.act.R
import com.roblesdotdev.act.core.ui.theme.ACTasksTheme
import com.roblesdotdev.act.taskList.domain.model.Task

@Composable
fun TaskListContent(
    viewState: TaskListViewState,
    onRescheduleClicked: (Task) -> Unit,
    onDoneClicked: (Task) -> Unit,
    onAddButtonClicked: () -> Unit
) {
    if (viewState is TaskListViewState.Loaded) {
        LoadedTasksContent(
            viewState,
            onRescheduleClicked = onRescheduleClicked,
            onDoneClicked = onDoneClicked,
            onAddButtonClicked = onAddButtonClicked,
        )
    }
}

@Composable
fun LoadedTasksContent(
    viewState: TaskListViewState.Loaded,
    onAddButtonClicked: () -> Unit,
    onDoneClicked: (Task) -> Unit,
    onRescheduleClicked: (Task) -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddButtonClicked) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_task),
                )
            }
        },
        topBar = {
            TaskListToolBar()
        }
    ) { paddingValues ->
        TaskList(
            tasks = viewState.tasks,
            onRescheduleClicked = onRescheduleClicked,
            onDoneClicked = onDoneClicked,
            modifier = Modifier
                .padding(paddingValues)
        )
    }
}

@Composable
fun TaskListToolBar() {
    Surface(
        color = MaterialTheme.colors.primary,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(84.dp)
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "TODO:",
                    modifier = Modifier
                        .size(84.dp)
                )
            }
            Text(
                text = stringResource(R.string.today),
                modifier = Modifier
                    .weight(1F),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold,
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "TODO:",
                    modifier = Modifier
                        .size(84.dp)
                )
            }
        }
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun TaskListContentPreview() {
    val tasks = (1..10).map { index ->
        Task(description = "Test task: $index")
    }
    val viewState = TaskListViewState.Loaded(tasks)
    ACTasksTheme {
        TaskListContent(
            viewState = viewState,
            onRescheduleClicked = {},
            onDoneClicked = {},
            onAddButtonClicked = {},
        )
    }
}
