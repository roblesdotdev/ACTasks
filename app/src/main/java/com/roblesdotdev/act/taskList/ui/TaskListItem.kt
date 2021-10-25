package com.roblesdotdev.act.taskList.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.roblesdotdev.act.R
import com.roblesdotdev.act.core.ui.theme.ACTasksTheme
import com.roblesdotdev.act.taskList.domain.model.Task

@Composable
fun TaskListItem(
    task: Task,
    onRescheduleClicked: () -> Unit,
    onDoneClicked: () -> Unit,
) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.End,
        ) {
            TaskText(text = task.description)

            ButtonRow(onRescheduleClicked, onDoneClicked)
        }
    }
}

@Composable
private fun ButtonRow(
    onRescheduleClicked: () -> Unit,
    onDoneClicked: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        RescheduleButton(onClick = onRescheduleClicked)
        DoneButton(onClick = onDoneClicked)
    }
}

@Composable
private fun RescheduleButton(onClick: () -> Unit) {
    TextButton(onClick) {
        Text(text = stringResource(R.string.reschedule))
    }
}

@Composable
private fun DoneButton(onClick: () -> Unit) {
    TextButton(onClick) {
        Text(text = stringResource(R.string.done))
    }
}

@Composable
private fun TaskText(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    )
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
fun TaskListItemPreview() {
    val task = Task(description = "This is a task description.")
    ACTasksTheme {
        TaskListItem(task, onRescheduleClicked = {}, onDoneClicked = {})
    }
}
