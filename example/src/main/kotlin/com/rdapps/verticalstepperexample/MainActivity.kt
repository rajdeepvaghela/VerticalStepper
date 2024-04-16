package com.rdapps.verticalstepperexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rdapps.stepper.Step
import com.rdapps.stepper.StepData
import com.rdapps.stepper.StepState
import com.rdapps.verticalstepperexample.ui.theme.VerticalStepperTheme

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VerticalStepperTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        var stageList by remember {
            mutableStateOf(
                listOf(
                    StepData(
                        id = 1L,
                        stepState = StepState.InitiallyAnimating,
                        title = "Title",
                        bodyText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
                    ),
                    StepData(
                        id = 2L,
                        stepState = StepState.InQueue,
                        title = "Title",
                        bodyText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
                    ),
                    StepData(
                        id = 3L,
                        stepState = StepState.InQueue,
                        title = "Title",
                        bodyText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                        isLast = true
                    )
                )
            )
        }

        stageList.forEach {
            Step(
                stepData = it,
                useAlternateComponent = it.id == 2L,
                alternateComponent = {
                    Button(onClick = { }) {
                        Text(text = "Done")
                    }
                }
            ) {
                val currentElement = stageList.firstOrNull { stage ->
                    stage.stepState == StepState.InitiallyAnimating
                }

                val nextElement = stageList.firstOrNull { stage ->
                    stage.stepState == StepState.InQueue
                }

                stageList = stageList.map { stage ->
                    if (stage.id == currentElement?.id) {
                        stage.copy(stepState = StepState.Visible)
                    } else if (stage.id == nextElement?.id) {
                        stage.copy(stepState = StepState.InitiallyAnimating)
                    } else {
                        stage
                    }
                }

                if (nextElement == null) {
                    stageList = stageList.map { stage ->
                        if (stage.id == 1L) {
                            stage.copy(stepState = StepState.Active())
                        } else {
                            stage
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    VerticalStepperTheme {
        MainScreen()
    }
}