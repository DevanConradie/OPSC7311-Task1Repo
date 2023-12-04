package com.example.opsc7311_task1

import android.content.Context
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.graphics.Typeface
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class Statistics : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val barChartView = BarChartView(this)
        barChartView.id = View.generateViewId()

        val constraintLayout = ConstraintLayout(this)
        constraintLayout.addView(barChartView)

        setContentView(constraintLayout)
    }
}

class BarChartView(context: Context) : View(context) {

    private val dataPoints = listOf(20, 40, 30, 50, 10, 60, 45, 25, 35, 15)
    private val dateLabels = listOf("Day 1", "Day 2", "Day 3", "Day 4", "Day 5", "Day 6", "Day 7", "Day 8", "Day 9", "Day 10")

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Set the background color
        canvas.drawColor(Color.parseColor("#0D47A1")) // Dark Blue

        val paint = Paint()
        paint.shader = LinearGradient(0f, 0f, 0f, height.toFloat(), Color.BLUE, Color.MAGENTA, Shader.TileMode.CLAMP)
        paint.isAntiAlias = true

        val textPaint = Paint()
        textPaint.color = Color.WHITE // Set text color to white
        textPaint.textSize = resources.getDimension(R.dimen.chart_text_size)
        textPaint.typeface = Typeface.DEFAULT_BOLD

        val titlePaint = Paint()
        titlePaint.color = Color.WHITE // Set text color to white for the title
        titlePaint.textSize = resources.getDimension(R.dimen.chart_title_size)
        titlePaint.typeface = Typeface.DEFAULT_BOLD

        val height = height.toFloat()
        val width = width.toFloat()
        val dataSize = dataPoints.size

        val barCount = dataSize
        val totalSpace = (barCount - 1) * 2 // 2 pixels space between bars
        val totalBarWidth = width - totalSpace
        val barWidth = totalBarWidth / barCount.toFloat()
        val bottomSpacing = height * 0.1f

        // Draw title at the top of the view
        val titleText = "Minutes Spent over the past 10 days"
        val titleTextWidth = titlePaint.measureText(titleText)
        val titleTextX = (width - titleTextWidth) / 2
        val titleTextY = height * 0.1f - 10 // Move up from the top

        canvas.drawText(titleText, titleTextX, titleTextY, titlePaint)

        for (i in 0 until dataSize) {
            val x = i * (barWidth + 2) // 2 pixels space between bars
            val y = height - ((dataPoints[i] * 0.5f) * height / 100) - bottomSpacing // Move towards the bottom

            // Draw the bar with gradient
            paint.shader = LinearGradient(x, 0f, x + barWidth, height.toFloat(), Color.BLUE, Color.MAGENTA, Shader.TileMode.CLAMP)
            canvas.drawRect(x, y - bottomSpacing, x + barWidth, height, paint)

            // Draw the text vertically from bottom to top
            val text = "${dateLabels[i]}: ${dataPoints[i]} Minutes"
            val textWidth = textPaint.measureText(text)

            // Draw the text centered, at the bottom, and shifted to the right
            val textX = x + (barWidth - textWidth) / 2 + 2 * barWidth // Shift to the right by 2 bar widths
            val textY = height - 20 // Move towards the bottom

            // Rotate the canvas for vertical text
            canvas.save()
            canvas.rotate(-90f, textX, textY)
            canvas.drawText(text, textX, textY, textPaint)
            canvas.restore()
        }
    }
}
