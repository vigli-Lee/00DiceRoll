package mobile.vigli.com.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var diceTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         diceTextView = findViewById(R.id.diceTextView)
    }

    fun onClick(v: View) {
        val diceNumber = Random.nextInt(6) + 1
        diceTextView.text = diceNumber.toString()
    }
}
