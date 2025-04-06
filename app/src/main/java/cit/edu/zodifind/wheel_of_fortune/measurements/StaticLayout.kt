package cit.edu.zodifind.wheel_of_fortune.measurements

import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.text.TextUtils

fun measureText(
    text: String,
    paint: TextPaint,
    width: Int,
    align: Layout.Alignment = Layout.Alignment.ALIGN_CENTER,
    maxLines: Int = Int.MAX_VALUE
) = StaticLayout.Builder.obtain(text, 0, text.length, paint, width)
    .setAlignment(align)
    .setMaxLines(maxLines)
    .setEllipsize(TextUtils.TruncateAt.END)
    .build()

