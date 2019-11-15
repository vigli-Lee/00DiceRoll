package mobile.vigli.com.diceroller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import mobile.vigli.com.diceroller.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result)

        val nickname = intent.getStringExtra("PARAM_NICKNAME")?: ""
        val isCompleted = intent.getBooleanExtra("PARAM_COMPLETE", false)

        binding.nickNameTextView.text = nickname
        binding.scoreTextView.text = if (isCompleted) "축하합니다.\n 운이 아주 좋으시군요!" else "아쉬워요. 다음엔 맞추실수 있을거에요!"
    }
}
