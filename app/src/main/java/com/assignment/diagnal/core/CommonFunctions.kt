package com.assignment.diagnal.core

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.removeItemDecorations() {
    while (this.itemDecorationCount > 0) {
        this.removeItemDecorationAt(0)
    }
}

fun RecyclerView.addGridItemDecorations(spacing: Int, spanCount: Int) {
    this.addItemDecoration(
        GridSpacingItemDecoration(
            spanCount, spacing, true
        )
    )
}