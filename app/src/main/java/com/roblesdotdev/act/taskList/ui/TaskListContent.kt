package com.roblesdotdev.act.taskList.ui

import android.content.res.Configuration
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
        }
    ) {
        TaskList(
            tasks = viewState.tasks,
            onRescheduleClicked = onRescheduleClicked,
            onDoneClicked = onDoneClicked
        )
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
