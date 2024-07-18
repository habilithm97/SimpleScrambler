package com.example.cubescrambler2404

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import com.example.cubescrambler2404.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val faces = listOf("U", "D", "L", "R", "F", "B")
    private val rotation = listOf("", "'", "2")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        binding.tvScramble.setOnTouchListener { view, motionEvent ->
            when(motionEvent.action) {
                MotionEvent.ACTION_UP -> {
                    binding.tvScramble.text = createScramble()
                }
            }
            true
        }
    }

    private fun createScramble(moves: Int = 20) : String {
        val scramble = mutableListOf<String>()
        var lastFace = ""
        var secondLastFace = ""

        for (i in 0 until moves) {
            var face: String
            do {
                face = faces[Random.nextInt(faces.size)]
            // 1. 마지막으로 사용된 면과 같으면 다시
            // 2. 마지막으로 사용된 면과 같은 축이고 두 번째 마지막으로 사용된 면이 같으면 다시
            } while (face == lastFace || (face == secondLastFace && getAxis(face) == getAxis(lastFace)))

            val rotation = rotation[Random.nextInt(rotation.size)]
            scramble.add(face + rotation)

            secondLastFace = lastFace
            lastFace = face
        }
        return scramble.joinToString(" ")
    }
    // 각 면에 대한 축을 반환
    private fun getAxis(face: String): String {
        return when (face) {
            "U", "D" -> "UD"
            "L", "R" -> "LR"
            "F", "B" -> "FB"
            else -> ""
        }
    }
}
