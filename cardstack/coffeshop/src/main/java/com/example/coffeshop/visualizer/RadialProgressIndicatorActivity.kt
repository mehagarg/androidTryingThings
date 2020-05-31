/*
* Copyright (C) 2014 [Jag Saund]
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

import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeshop.R
import com.example.coffeshop.visualizer.widgets.RadialProgressIndicator
import java.lang.ref.WeakReference

class RadialProgressIndicatorActivity : AppCompatActivity() {
    private var indicator: RadialProgressIndicator? = null
    private var handler: RadialProgressUpdateHandler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_radial_progress_indicator)
        indicator = findViewById(R.id.radial_progress_indicator)
        handler = RadialProgressUpdateHandler(this)
        val updatePrimaryIndicator = handler!!.obtainMessage(
            RadialProgressUpdateHandler.MSG_ID_UPDATE_PRIMARY_INDICATOR,
            100,
            0
        )
        handler!!.sendMessageDelayed(updatePrimaryIndicator, 1500)
        val updateSecondaryIndicator = handler!!.obtainMessage(
            RadialProgressUpdateHandler.MSG_ID_UPDATE_SECONDARY_INDICATOR,
            5,
            0
        )
        handler!!.sendMessageDelayed(updateSecondaryIndicator, 500)
    }

    private class RadialProgressUpdateHandler internal constructor(activity: RadialProgressIndicatorActivity) :
        Handler() {
        private val reference: WeakReference<RadialProgressIndicatorActivity>
        override fun handleMessage(msg: Message) {
            if (msg.what == MSG_ID_UPDATE_PRIMARY_INDICATOR) {
                val amount = if (msg.arg1 > 0) msg.arg1 else 100
                val activity = reference.get()
                if (activity != null) {
                    val max: Int = activity.indicator!!.getMaxProgress()
                    val progress: Int = activity.indicator!!.getProgress() + amount
                    activity.indicator?.setProgress(progress)
                    if (progress < max) {
                        val update = obtainMessage(
                            MSG_ID_UPDATE_PRIMARY_INDICATOR,
                            100,
                            0
                        )
                        sendMessageDelayed(update, 1500)
                    }
                }
            } else if (msg.what == MSG_ID_UPDATE_SECONDARY_INDICATOR) {
                val amount = if (msg.arg1 > 0) msg.arg1 else 10
                val activity = reference.get()
                if (activity != null) {
                    val max = 100
                    val progress: Int = activity.indicator!!.secondaryProgress + amount
                    activity.indicator?.setSecondaryProgress(progress)
                    if (progress < max) {
                        val update = obtainMessage(
                            MSG_ID_UPDATE_SECONDARY_INDICATOR,
                            5,
                            0
                        )
                        sendMessageDelayed(update, 500)
                    }
                }
            }
        }

        companion object {
            internal const val MSG_ID_UPDATE_PRIMARY_INDICATOR = 1
            const val MSG_ID_UPDATE_SECONDARY_INDICATOR = 2
        }

        init {
            reference = WeakReference(activity)
        }
    }
}