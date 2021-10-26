package com.roblesdotdev.act.taskList.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.roblesdotdev.act.R
import com.roblesdotdev.act.core.ui.theme.ACTasksTheme
import com.roblesdotdev.act.taskList.domain.model.Task

@Composable
fun TaskList(
    tasks: List<Task>,
    onRescheduleClicked: (Task) -> Unit,
    onDoneClicked: (Task) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.list_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.list_padding)),
        modifier = modifier,
    ) {
        items(tasks) { task ->
            TaskListItem(
                task,
                onRescheduleClicked = {
                    onRescheduleClicked(task)
                },
                onDoneClicked = {
                    onDoneClicked(task)
                }
            )
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
fun TaskListPreview() {
    val tasks = (1..10).map { index ->
        Task(description = "Test task: $index")
    }
    ACTasksTheme {
        TaskList(
            tasks,
            onRescheduleClicked = {},
            onDoneClicked = {},
        )
    }
}
