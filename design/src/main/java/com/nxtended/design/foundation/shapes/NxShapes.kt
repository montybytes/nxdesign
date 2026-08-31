package com.nxtended.design.foundation.shapes

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

object NxShapes {
    val None = RoundedCornerShape(0.dp)

    val Default = RoundedCornerShape(4.dp)

    val Large = RoundedCornerShape(12.dp)

    val Card = RoundedCornerShape(24.dp)

    val Pill = RoundedCornerShape(100)

    val Start =
        RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp, bottomStart = 4.dp, bottomEnd = 4.dp)

    val End =
        RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp, bottomStart = 12.dp, bottomEnd = 12.dp)
}