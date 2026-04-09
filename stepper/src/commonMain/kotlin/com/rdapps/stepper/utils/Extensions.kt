package com.rdapps.stepper.utils

import androidx.compose.ui.Modifier

inline fun Modifier.doIf(
    condition: Boolean,
    ifFalse: Modifier.() -> Modifier = { this },
    ifTrue: Modifier.() -> Modifier
): Modifier = if (condition) this.ifTrue() else this.ifFalse()