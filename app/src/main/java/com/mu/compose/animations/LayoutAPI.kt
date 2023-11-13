package com.mu.compose.animations

import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@Composable
fun LayoutAPI() {
    var expended by remember { mutableStateOf(false) }
    val offset by animateIntOffsetAsState(
        targetValue = if (expended) IntOffset(0, 200) else IntOffset.Zero,
        label = ""
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Green)
        )

        Box(
            modifier = Modifier
                .layout { measurable, constraints ->
                    val placeable = measurable.measure(constraints)
                    layout(placeable.width + offset.x, placeable.height + offset.y) {
                        placeable.placeRelative(offset)
                    }
                }
                .size(100.dp)
                .background(Color.Yellow)
                .clickable { expended = !expended }
        )

        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Green)
        )
    }
}