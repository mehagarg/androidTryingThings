package com.example.coffeshop.visualizer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeshop.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.start_audio_visualizer).setOnClickListener {
            val intent = Intent(this@MainActivity, AudioVisualizerActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.start_progress_indicator).setOnClickListener {
            val intent =
                Intent(this@MainActivity, RadialProgressIndicatorActivity::class.java)
            startActivity(intent)
        }
    }
}