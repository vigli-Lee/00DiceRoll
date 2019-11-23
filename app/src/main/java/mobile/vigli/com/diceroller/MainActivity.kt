package mobile.vigli.com.diceroller

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var rollButton: Button
    private lateinit var diceImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rollButton = findViewById(R.id.rollButton)
        diceImageView = findViewById(R.id.diceImageView)

        //주사위 굴리기 버튼 클릭
        rollButton.setOnClickListener {
            playGame()
        }
    }

    /**
     * 주사위 일치 게임을 한다.
     *
     */
    private fun playGame() {
        val diceNumber = Random.nextInt(6) + 1
        diceImageView.setImageResource(getRandomDiceImage(diceNumber))
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
}
