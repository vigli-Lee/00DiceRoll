package mobile.vigli.com.diceroller

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val nickname = intent.getStringExtra("PARAM_NICKNAME")?: ""
        val isCompleted = intent.getBooleanExtra("PARAM_COMPLETE", false)

        findViewById<TextView>(R.id.nickNameTextView).text = nickname
        findViewById<TextView>(R.id.scoreTextView).text = if (isCompleted) "축하합니다.\n 운이 아주 좋으시군요!" else "아쉬워요. 다음엔 맞추실수 있을거에요!"
    }
}
