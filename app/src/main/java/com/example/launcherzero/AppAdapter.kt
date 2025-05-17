package com.example.launcherzero

import android.content.Context
import android.content.pm.PackageManager
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.content.pm.ResolveInfo

class AppAdapter(
    private val context: Context,
    private val apps: List<ResolveInfo>,
    private val pm: PackageManager
) : BaseAdapter() {

    override fun getCount() = apps.size
    override fun getItem(position: Int) = apps[position]
    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val app = apps[position]
        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(16, 16, 16, 16)

        val icon = ImageView(context)
        icon.setImageDrawable(app.loadIcon(pm))
        layout.addView(icon)

        val label = TextView(context)
        label.text = app.loadLabel(pm)
        layout.addView(label)

        return layout
    }
}