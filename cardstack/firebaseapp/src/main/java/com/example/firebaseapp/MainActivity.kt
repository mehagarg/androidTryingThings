package com.example.firebaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

private const val PARAMETER_SHOULD_SHOW_CHAT = "parameter_should_show_chat"

class MainActivity : AppCompatActivity() {

    private lateinit var fireBaseRemoteConfig: FirebaseRemoteConfig
    private var shouldShowChatActivity: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fireBaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        fireBaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)

        shouldShowChatActivity = fireBaseRemoteConfig.getBoolean(PARAMETER_SHOULD_SHOW_CHAT)

        setupHelpButton()
    }

    override fun onStart() {
        super.onStart()
        fetchRemoteConfigParameters()
    }

    private fun fetchRemoteConfigParameters() {
        fireBaseRemoteConfig.fetch()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    fireBaseRemoteConfig.activateFetched()
                }
                shouldShowChatActivity = fireBaseRemoteConfig.getBoolean(PARAMETER_SHOULD_SHOW_CHAT)
            }
    }

    private fun setupHelpButton() {
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            if (shouldShowChatActivity) {
                startChatActivity()
            } else {
                startFaqActivity()
            }
        }
    }

    private fun startFaqActivity() {
        Toast.makeText(this, "Faq Activity", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, FAQActivity::class.java))
    }

    private fun startChatActivity() {
        Toast.makeText(this, "Chat Activity", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, ChatActivity::class.java))
    }
}
