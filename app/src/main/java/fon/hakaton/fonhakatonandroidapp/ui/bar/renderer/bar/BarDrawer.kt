package fon.hakaton.fonhakatonandroidapp.ui.bar.renderer.bar

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.drawscope.DrawScope
import fon.hakaton.fonhakatonandroidapp.ui.bar.BarChartData

interface BarDrawer {
  fun drawBar(
    drawScope: DrawScope,
    canvas: Canvas,
    barArea: Rect,
    bar: BarChartData.Bar
  )
}