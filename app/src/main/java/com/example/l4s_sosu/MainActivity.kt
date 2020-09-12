package com.example.l4s_sosu

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    //companion objectはクラス内に作成されるSingleton（指定したクラスのインスタンスが1つしか存在しないことを保証するもの）
    companion object{
        //privateをつけると宣言したクラスからのみアクセスできる
        //定数を宣言するときはconstをつける　const を付与すると getter を介さずに直接呼ぶことが可能
        //→ QUESTION_COUNTは他の場所からいじられたくない定義
        private const val QUESTION_COUNT: Int = 10
    }

    var random : Random = Random()
    //questionsって名前の配列をつける
    val questions: IntArray = IntArray(QUESTION_COUNT)
    //pointってint型の変数を作る → 正解したらプラスする
    var point: Int = 0
    //回答した数
    var answerCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //1000までの数字を10個ランダムに生成して、questionsの配列を作る
        //Javaでいうfor(i = 0; i < QUESTION_COUNT; i++)。 QUESTION_COUNT回繰り返してる
        for (i in 0 until QUESTION_COUNT){
            //number乱数の生成 0〜999の数を生成する
            val number = random.nextInt(1000)
            //logcatにD/Number: Question*** と表示する
            Log.d("Number","Question"+number.toString())
            //10個のランダムな数字が入った配列ができる
            questions[i] = number
        }

        //変数の初期化をする
        point = 0
        answerCount = 0
        //ここでquestions[answerCount]はquestions[0]と同義 さっきのfor文で生成されたランダムな数字の一つ目が表示される
        textView.text = questions[answerCount].toString()
        textView.setTextColor(Color.BLACK)
    }

    //○ボタンが押された時の処理
    fun maru (v : View){

        if (isPrime(questions[answerCount])){
            //answer = trueなら、割り切れなかったということで素数 → ⚪︎を押している時の処理なので正解
            // toastは画面にメッセージを出す
            Toast.makeText(this,"正解", Toast.LENGTH_SHORT).show()
            //正解数をプラス１する
            point++
            //logcatにD/maru: 正解と表示する
            Log.d( "maru","正解"+ point.toString())
        }else{
            //answer = falseなら、割り切れたということで素数でない → ⚪︎を押している時の処理なので不正解
            Toast.makeText(this,"不正解", Toast.LENGTH_SHORT).show()
            Log.d( "maru","不正解")
        }

        //答えた数を１プラスする
        answerCount++

        //答えた数が問題数と同じになったら
        if (answerCount == QUESTION_COUNT){
            //textViewに正解数を*点と赤色で表示する
            textView.text = point.toString() + "点"
            textView.setTextColor(Color.RED)

            //正解数と答えた数を初期化する
            point = 0
            answerCount = 0
        }else{
            //全部答えていなかったら　次のランダムに生成された数を黒色で表示する
            textView.text =questions[answerCount].toString()
            textView.setTextColor(Color.BLACK)
        }
    }

    //○ボタンが押された時の処理
    fun batsu (v : View){
        if (isPrime(questions[answerCount])){
            //answer = trueなら、割り切れなかったということで素数 → ×を押している時の処理なので不正解
            // toastは画面にメッセージを出す
            Toast.makeText(this,"不正解", Toast.LENGTH_SHORT).show()
            //logcatにD/maru: 不正解と表示する
            Log.d( "maru","不正解")
        }else{
            //answer = falseなら、割り切れたということで素数でない → ×を押している時の処理なので正解
            Toast.makeText(this,"正解", Toast.LENGTH_SHORT).show()
            //正解数をプラス１する
            point++
            //logcatにD/maru: 正解と表示する
            Log.d( "maru","正解"+ point.toString())
        }

        //答えた数を１プラスする
        answerCount++

        //答えた数が問題数と同じになったら
        if (answerCount == QUESTION_COUNT){
            //textViewに正解数を*点と赤色で表示する
            textView.text = point.toString() + "点"
            textView.setTextColor(Color.RED)

            //正解数と答えた数を初期化する
            point = 0
            answerCount = 0
        }else{
            //全部答えていなかったら　次のランダムに生成された数を黒色で表示する
            textView.text =questions[answerCount].toString()
            textView.setTextColor(Color.BLACK)
        }
    }

    fun isPrime(number:Int):Boolean{

        //ここで素数かどうか判定してる　
        //number（ランダムに生成された数）を2〜number-1までの数で繰り返し割り算していく
        for (i in 2 until number){
            //もし割った余りが0だったら
            if (number % i == 0){
                //割り切れたということでnumberは素数ではない
                return false
                Log.d( "answer","falseです" + i + "で割り切れました")
                //　breakは処理を終わらせる　for文の中の処理を終わり、次の処理に進む
                break
            }
            Log.d( "answer","trueです")
        }
        return true
    }
}
