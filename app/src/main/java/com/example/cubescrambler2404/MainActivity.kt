package com.example.cubescrambler2404

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.PopupMenu
import com.example.cubescrambler2404.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val faces = listOf("U", "R", "F", "B", "L", "B")
    private val rotation = listOf("", "'", "2")
    private var moves = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        binding.apply {
            tvScramble.setOnTouchListener { _, motionEvent ->
                when(motionEvent.action) {
                    MotionEvent.ACTION_UP -> {
                        binding.tvScramble.text = createScramble()
                    }
                }
                true
            }
            tvCube.setOnTouchListener { view, motionEvent ->
                when(motionEvent.action) {
                    MotionEvent.ACTION_UP -> {
                        showPopupMenu(view)
                    }
                }
                true
            }
        }
    }

    private fun createScramble() : String {
        val scramble = mutableListOf<String>()
        var lastFace = ""
        var secondLastFace = ""

        val facesToUse = if(moves == 9) {
            faces.subList(0, 3)
        } else {
            faces
        }
        repeat(moves) {
            var face: String
            do {
                face = facesToUse.random()
            // 1. 마지막으로 사용된 면과 같으면 다시
            // 2. 마지막으로 사용된 면과 같은 축이고 두 번째 마지막으로 사용된 면이 같으면 다시
            } while (face == lastFace || (face == secondLastFace && getAxis(face) == getAxis(lastFace)))

            val rotation = rotation.random()
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

    private fun showPopupMenu(view: View) {
        PopupMenu(this@MainActivity, view).apply {
            menuInflater.inflate(R.menu.popup, menu)
            setOnMenuItemClickListener { menuItem -> onMenuItemClick(menuItem) }
            show()
        }
    }

    private fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item1 -> {
                moves = 20
                binding.apply {
                    tvCube.text = getString(R.string.three)
                    tvScramble.text = createScramble()
                }
            }
            R.id.item2 -> {
                moves = 9
                binding.apply {
                    tvCube.text = getString(R.string.two)
                    tvScramble.text = createScramble()
                }
            }
        }
        return true
    }
}
