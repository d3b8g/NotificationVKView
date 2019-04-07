package net.d3b8g.notificationvkview.Helpers

import android.content.Context
import android.graphics.Typeface

fun setFont(context: Context):Typeface {
    return Typeface.createFromAsset(context.assets,"Fonts/SFUIText-MediumItalic.ttf")
}