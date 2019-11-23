package mobile.vigli.com.diceroller

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var currentCount = 0
    private var checkCount = 0

    private lateinit var countTextView: TextView
    private lateinit var nickNameEditText: EditText
    private lateinit var numberEditText: EditText
    private lateinit var rollButton: Button
    private lateinit var diceImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countTextView = findViewById(R.id.countTextView)
        nickNameEditText = findViewById(R.id.nickNameEditText)
        numberEditText = findViewById(R.id.numberEditText)
        rollButton = findViewById(R.id.rollButton)
        diceImageView = findViewById(R.id.diceImageView)

        //주사위 굴리기 버튼 클릭
        rollButton.setOnClickListener {
            if (isValidNickname(nickNameEditText.text.toString())) {
                val number = numberEditText.text.toString()
                if (isValidNumber(number)) {
                    playGame(number.toInt())
                } else {
                    numberEditText.text.clear()
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
        diceImageView.setImageResource(getRandomDiceImage(diceNumber))
        if (diceNumber === number) checkCount++
        countTextView.text = String.format(RESULT_FORMAT, checkCount, currentCount, TOTAL_GAME)

        //게임 종료 확인
        if (currentCount === TOTAL_GAME) {
            rollButton.isEnabled = false

            val isCompleted = checkCount > 1

            Toast.makeText(this, if(isCompleted) "성공" else "실패", Toast.LENGTH_SHORT).show()
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
