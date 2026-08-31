package com.nxtended.design.foundation.shapes

import androidx.compose.foundation.shape.RoundedCornerShape


fun listItemShape(index: Int, listLength: Int): RoundedCornerShape {
    return when {
        listLength == 1 -> NxShapes.Large
        index == 0 -> NxShapes.Start
        index == listLength - 1 -> NxShapes.End
        else -> NxShapes.Default
    }
}