package aohanyao.com.kotlindemo

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

class BasicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)
        val toolbar = findViewById(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)

        val fab = findViewById(R.id.fab) as FloatingActionButton?
        fab!!.setOnClickListener({ view -> Snackbar.make(view, "Replace with your own action" + add(1, 2), Snackbar.LENGTH_LONG).setAction("Action", null).show() })
    }

    fun add(x: Int, y: Int): Int {

        return x + y
    }
}
