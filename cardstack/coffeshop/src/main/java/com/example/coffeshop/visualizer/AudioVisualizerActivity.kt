/*
* Copyright (C) 2015 [Jag Saund]
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.coffeshop.visualizer

import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.coffeshop.R
import com.example.coffeshop.visualizer.widgets.AudioVisualizer
import java.io.IOException

/**
 * AudioVisualizerActivity demonstrates using two of the prebuilt audio visualization renders.
 * The classic audio visualizer displays the frequency buckets similar to the typical audio
 * spectrum with vertical bars increasing / decreasing according to the magnitude of the frequency.
 * The modern audio visualizer is similar to the classic but centers the bars vertically and grows
 * in both the up and down vertical directions.
 */
class AudioVisualizerActivity : AppCompatActivity() {
    private class OnMediaPreparedListener internal constructor(private val v: ActiveAnalyzerHolder) :
        OnPreparedListener {
        override fun onPrepared(mp: MediaPlayer) {
            mp.start()
            v.activeAnalyzer?.setAudioSessionID(mp.audioSessionId)
            v.activeAnalyzer?.start()
        }

    }

    private class OnMediaCompletedListener internal constructor(private val v: ActiveAnalyzerHolder) :
        OnCompletionListener {
        override fun onCompletion(mp: MediaPlayer) {
            v.activeAnalyzer?.stop()
        }

    }

    /**
     * Active Analyzer Holder allows us to swap the active analyzer reference
     * while still maintaining a static class implementation of the media playback
     *
     */
    private class ActiveAnalyzerHolder internal constructor() {
        var activeAnalyzer: AudioVisualizer? = null
        fun setActiveVisualizer(visualizer: AudioVisualizer?) {
            activeAnalyzer = visualizer
        }
    }

    private var activeAnalyzerHolder: ActiveAnalyzerHolder? = null
    private var classicAnalyzer: AudioVisualizer? = null
    private var modernAnalyzer: AudioVisualizer? = null
    private var player: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_visualizer)
        val toolbar: Toolbar = findViewById(R.id.audio_visualizer_toolbar)
        setSupportActionBar(toolbar)
        classicAnalyzer = findViewById(R.id.classic_analyzer)
        classicAnalyzer?.visibility = View.VISIBLE
        classicAnalyzer?.fftBucketSize = 10
        modernAnalyzer = this.findViewById<AudioVisualizer>(R.id.modern_analyzer)
        modernAnalyzer?.visibility = View.GONE
        modernAnalyzer?.smoothingFactor = 0.5f
        val toggleAnalyzer = findViewById<Button>(R.id.button_toggle_analyzer)
        toggleAnalyzer.setOnClickListener {
            if (classicAnalyzer?.visibility === View.VISIBLE) {
                classicAnalyzer?.stop()
                classicAnalyzer?.setVisibility(View.GONE)
                modernAnalyzer?.setVisibility(View.VISIBLE)
                modernAnalyzer?.setAudioSessionID(player!!.audioSessionId)
                modernAnalyzer?.start()
                toggleAnalyzer.setText(R.string.analyzer_toggle_show_classic)
                activeAnalyzerHolder!!.setActiveVisualizer(modernAnalyzer)
            } else {
                modernAnalyzer?.stop()
                modernAnalyzer?.setVisibility(View.GONE)
                classicAnalyzer?.setVisibility(View.VISIBLE)
                classicAnalyzer?.setAudioSessionID(player!!.audioSessionId)
                classicAnalyzer?.start()
                toggleAnalyzer.setText(R.string.analyzer_toggle_show_modern)
                activeAnalyzerHolder!!.setActiveVisualizer(classicAnalyzer)
            }
        }
        activeAnalyzerHolder = ActiveAnalyzerHolder()
        activeAnalyzerHolder!!.setActiveVisualizer(classicAnalyzer)
        val onPreparedListener: OnPreparedListener = OnMediaPreparedListener(activeAnalyzerHolder!!)
        val onCompletionListener: OnCompletionListener =
            OnMediaCompletedListener(activeAnalyzerHolder!!)
        player = MediaPlayer()
        player!!.setOnPreparedListener(onPreparedListener)
        player!!.setOnCompletionListener(onCompletionListener)
        try {
            player!!.setDataSource(this, MEDIA_URL)
            player!!.prepareAsync()
        } catch (e: IOException) {
            Log.e(
                TAG,
                "Failed to set media player URL.",
                e
            )
        }
    }

    override fun onResume() {
        super.onResume()
        if (player!!.isPlaying) {
            activeAnalyzerHolder!!.activeAnalyzer?.start()
        }
    }

    override fun onPause() {
        super.onPause()
        activeAnalyzerHolder!!.activeAnalyzer?.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (player != null) {
            try {
                player!!.stop()
                player!!.release()
            } catch (ignore: Exception) {
            }
        }
    }

    companion object {
        private val TAG = AudioVisualizerActivity::class.java.simpleName
        private val MEDIA_URL =
            Uri.parse("http://www.djdivsa.com/uploads/podcasts/MidnightCityII.mp3")
    }
}