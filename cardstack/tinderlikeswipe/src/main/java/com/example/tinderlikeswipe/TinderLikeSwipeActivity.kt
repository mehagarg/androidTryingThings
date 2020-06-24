package com.example.tinderlikeswipe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.tinder_like_swipe_activity.*

class TinderLikeSwipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tinder_like_swipe_activity)
        scene_1.setOnClickListener {
            startActivity(
                Intent(this, TinderScene1Activity::class.java)
            )
        }
        scene_2.setOnClickListener {
            startActivity(
                Intent(this, TinderScene2Activity::class.java)
            )
        }
        scene_3.setOnClickListener {
            startActivity(
                Intent(this, TinderScene1Activity::class.java)
            )
        }
        scene_4.setOnClickListener {
            startActivity(
                Intent(this, TinderScene1Activity::class.java)
            )
        }
        scene_5.setOnClickListener {
            startActivity(
                Intent(this, TinderScene1Activity::class.java)
            )
        }
        scene_7.setOnClickListener {
            startActivity(
                Intent(this, TinderScene1Activity::class.java)
            )
        }
        scene_7.setOnClickListener {
            startActivity(
                Intent(this, TinderScene1Activity::class.java)
            )
        }
        scene_8.setOnClickListener {
            startActivity(
                Intent(this, TinderScene1Activity::class.java)
            )
        }
        scene_9.setOnClickListener {
            startActivity(
                Intent(this, TinderScene1Activity::class.java)
            )
        }
        scene_10.setOnClickListener {
            startActivity(
                Intent(this, TinderScene1Activity::class.java)
            )
        }
    }
}
