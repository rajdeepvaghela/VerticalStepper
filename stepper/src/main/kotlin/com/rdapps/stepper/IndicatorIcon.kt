package com.rdapps.stepper

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun IndicatorIcon(stepState: StepState, toBeAnimated: Boolean, onAnimationDone: () -> Unit) {
    Box(modifier = Modifier.size(MaterialTheme.typography.bodyMedium.fontSize.value.dp + 4.dp)) {
        when (stepState) {
            StepState.InitiallyAnimating -> {

                val animatedAlpha = remember {
                    Animatable(0f)
                }

                LaunchedEffect(stepState) {
                    if (toBeAnimated) {
                        animatedAlpha.animateTo(
                            targetValue = 1f,
                            animationSpec = tween(durationMillis = 500)
                        )
                        onAnimationDone()
                    }
                }

                Spacer(
                    modifier = Modifier
                        .alpha(animatedAlpha.value)
                        .matchParentSize()
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.onBackground,
                            shape = CircleShape
                        )
                )
            }

            StepState.Visible -> {
                Spacer(
                    modifier = Modifier
                        .matchParentSize()
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.secondary,
                            shape = CircleShape
                        )
                )
            }

            StepState.Loading -> {
                Spacer(
                    modifier = Modifier
                        .matchParentSize()
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.secondary,
                            shape = CircleShape
                        )
                )

                CircularProgressIndicator(
                    modifier = Modifier.matchParentSize(),
                    color = MaterialTheme.colorScheme.onBackground,
                    strokeWidth = 3.dp
                )
            }

            is StepState.Active -> {
                Spacer(
                    modifier = Modifier
                        .matchParentSize()
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.secondary,
                            shape = CircleShape
                        )
                )

                CircularProgressIndicator(
                    progress = {
                        stepState.progress
                    },
                    modifier = Modifier.matchParentSize(),
                    color = stepState.color,
                    strokeWidth = 3.dp,
                )
            }

            is StepState.Done -> {
                val animatedAlpha = remember {
                    Animatable(0f)
                }

                LaunchedEffect(stepState) {
                    animatedAlpha.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(durationMillis = 500)
                    )
                }

                Spacer(
                    modifier = Modifier
                        .alpha(animatedAlpha.value)
                        .matchParentSize()
                        .background(
                            color = Color.Green,
                            shape = CircleShape
                        )
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_check_24),
                    modifier = Modifier
                        .alpha(animatedAlpha.value)
                        .size(10.dp)
                        .align(Alignment.Center),
                    colorFilter = ColorFilter.tint(color = Color.White),
                    contentDescription = "Done"
                )
            }

            StepState.Error -> {
                val animatedAlpha = remember {
                    Animatable(0f)
                }

                LaunchedEffect(stepState) {
                    animatedAlpha.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(durationMillis = 500)
                    )
                }

                Spacer(
                    modifier = Modifier
                        .alpha(animatedAlpha.value)
                        .matchParentSize()
                        .background(
                            color = Color.Red,
                            shape = CircleShape
                        )
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_close_24),
                    modifier = Modifier
                        .alpha(animatedAlpha.value)
                        .size(10.dp)
                        .align(Alignment.Center),
                    colorFilter = ColorFilter.tint(color = Color.White),
                    contentDescription = "Error"
                )
            }

            StepState.InQueue -> {}
        }
    }
}