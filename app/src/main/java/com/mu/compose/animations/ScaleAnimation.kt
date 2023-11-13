package com.mu.compose.animations

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun ScaleAnimation() {
    var expended by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(targetValue = if (expended) 1.2f else 1f, label = "")
    val alpha by animateFloatAsState(targetValue = if (expended) 1f else 0.5f, label = "")
    val height by animateDpAsState(targetValue = if (expended) 400.dp else 100.dp, label = "")
    val color by animateColorAsState(
        targetValue = if (expended) Color.Yellow else Color.Red,
        label = ""
    )

    val pxToMove = with(LocalDensity.current) {
        100.dp.toPx().roundToInt()
    }

    val offset by animateIntOffsetAsState(
        targetValue = if (expended) IntOffset(0, pxToMove) else IntOffset.Zero,
        label = ""
    )

    Box(
        modifier = Modifier
            .offset { offset }
            .fillMaxWidth()
            .height(height)
            .graphicsLayer {
                this.alpha = alpha
            }
            .animateContentSize()
            .scale(scale = scale)
            .clickable {
                expended = !expended
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Scale Animation")
        }
    }
}