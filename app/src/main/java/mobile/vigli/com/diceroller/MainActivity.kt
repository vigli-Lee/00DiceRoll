package mobile.vigli.com.diceroller

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import mobile.vigli.com.diceroller.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentCount = 0
    private var checkCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //주사위 굴리기 버튼 클릭
        binding.rollButton.setOnClickListener {
            if (isValidNickname(binding.nickNameEditText.text.toString())) {
                val number = binding.numberEditText.text.toString()
                if (isValidNumber(number)) {
                    playGame(number.toInt())
                } else {
                    binding.numberEditText.text.clear()
                    showErrorToast("1~6 중 숫자를 적어주세요")
                }
            } else {
                //error
                showErrorToast("닉네임을 적어주세요")
            }
        }
    }

    /**
     * 주사위 일치 게임을 한다.
     *
     * @param number    입력 값
     */
    private fun playGame(number: Int) {
        currentCount++
        val diceNumber = Random.nextInt(6) + 1
        binding.diceImageView.setImageResource(getRandomDiceImage(diceNumber))
        if (diceNumber === number) checkCount++
        binding.countTextView.text = String.format(RESULT_FORMAT, checkCount, currentCount, TOTAL_GAME)

        //게임 종료 확인
        if (currentCount === TOTAL_GAME) {
            binding.rollButton.isEnabled = false

            val isCompleted = checkCount > 1

            startActivity(Intent(this, ResultActivity::class.java)
                    .putExtra("PARAM_NICKNAME", binding.nickNameEditText.text.toString())
                    .putExtra("PARAM_COMPLETE", isCompleted))
        }
    }

    /**
     * 에러 토스트 메시지를 띄운다.
     *
     * @param message   에러 메시지
     */
    private fun showErrorToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * 닉네임이 유효한지 검사한다.
     *
     * @param nickname  닉네임
     * @return
     */
    private fun isValidNickname(nickname: String): Boolean {
        return nickname.isNotEmpty()
    }

    /**
     * 입력 숫자가 유효한지 검사한다.
     *
     * @param number    숫자 포맷
     * @return
     */
    private fun isValidNumber(number: String): Boolean {
        return try {
            when (number.toInt()) {
                in 1..6 -> true
                else -> false
            }
        } catch (e: NumberFormatException) {
            false
        }
    }

    /**
     * 주사위 이미지 id를 가져온다.
     *
     * @param diceNumber    주사위 번호
     * @return
     */
    private fun getRandomDiceImage(diceNumber: Int): Int {
        return when (diceNumber) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.empty_dice
        }
    }

    companion object {
        const val TOTAL_GAME = 5
        const val RESULT_FORMAT = "일치한 횟수 %d(%d/%d)"
    }
}
