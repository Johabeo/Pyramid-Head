package com.example.room_with_view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val newPyramidActivityRequestCode = 1
    private val pyramidViewModel: PyramidViewModel by viewModels {
        PyramidViewModelFactory((application as PyramidApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = PyramidListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        pyramidViewModel.allPyramids.observe(this) { pyramids ->

            pyramids.let { adapter.submitList(it) }
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewPyramidActivity::class.java)
            startActivityForResult(intent, newPyramidActivityRequestCode)
        }

        pyramidViewModel.allPyramids.observe(this) { pyramids ->
            pyramids.let {adapter.submitList(it)}
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newPyramidActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(NewPyramidActivity.EXTRA_REPLY)?.let {reply ->
                val pyramid = Pyramid(0, reply, 3)
                pyramidViewModel.insert(pyramid)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }

}