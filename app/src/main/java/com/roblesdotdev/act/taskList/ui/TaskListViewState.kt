package com.roblesdotdev.act.taskList.ui

import com.roblesdotdev.act.core.ui.UIText
import com.roblesdotdev.act.taskList.domain.model.Task

sealed class TaskListViewState {
    object Loading : TaskListViewState()

    data class Loaded(
        val tasks: List<Task>
    ) : TaskListViewState()

    data class Error(
        val errorMessage: UIText,
    ) : TaskListViewState()
}
