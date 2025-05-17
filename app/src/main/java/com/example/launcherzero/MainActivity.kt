package com.example.launcherzero

import android.os.Bundle
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.GridView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.launcherzero.ui.theme.LauncherZeroTheme

import android.os.Handler
import android.os.Looper


class MainActivity : ComponentActivity() {
    override fun onResume() {
        super.onResume()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(android.provider.Settings.ACTION_SETTINGS)
            startActivity(intent)
        }, 10000) // 10 seconds
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gridView = GridView(this).apply {
            numColumns = 4
        }

        setContentView(gridView)

        val pm = packageManager
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)

        val apps = pm.queryIntentActivities(intent, 0)
        val adapter = AppAdapter(this, apps, pm)
        gridView.adapter = adapter

        gridView.setOnItemClickListener { _, _, position, _ ->
            val appIntent = pm.getLaunchIntentForPackage(apps[position].activityInfo.packageName)
            startActivity(appIntent)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LauncherZeroTheme {
        Greeting("Android")
    }
}