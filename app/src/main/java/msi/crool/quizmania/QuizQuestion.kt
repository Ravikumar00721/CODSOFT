package msi.crool.quizmania

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class QuizQuestion : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOption: Int = 0
    private var mUsername: String = ""
    private var mCorrectAnswer: Int = 0
    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvQuestion: TextView? = null
    private var image: ImageView? = null
    private var btn_1: Button? = null
    private var btn_2: Button? = null
    private var btn_3: Button? = null
    private var btn_4: Button? = null
    private var btn_submit: Button? = null
    private var mIsQuestionAnswered: Boolean = false
    private var mHasUserSubmitted: Boolean = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        progressBar = findViewById(R.id.progress_bar)
        tvProgress = findViewById(R.id.progress_text)
        tvQuestion = findViewById(R.id.question_view)
        image = findViewById(R.id.image_view)
        btn_1 = findViewById(R.id.option_1)
        btn_2 = findViewById(R.id.option_2)
        btn_3 = findViewById(R.id.option_3)
        btn_4 = findViewById(R.id.option_4)
        btn_submit = findViewById(R.id.option_submit)

        // Setting click listeners for buttons
        btn_1?.setOnClickListener(this)
        btn_2?.setOnClickListener(this)
        btn_3?.setOnClickListener(this)
        btn_4?.setOnClickListener(this)
        btn_submit?.setOnClickListener(this)

        mUsername = intent.getStringExtra(Constants.USER_NAME).toString()

        mQuestionList = Constants.getQuestion()
        setQuestion()
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion() {
        defaultOptionView()
        val question: Question = mQuestionList!![mCurrentPosition - 1]
        progressBar?.progress = mCurrentPosition
        tvProgress?.text = "$mCurrentPosition / ${progressBar?.max}"
        tvQuestion?.text = question.question
        btn_1?.text = question.optionOne
        btn_2?.text = question.optionTwo
        btn_3?.text = question.optionThree
        btn_4?.text = question.optionFour
        image?.setImageResource(question.image)

        if (mCurrentPosition == mQuestionList!!.size) {
            btn_submit?.text = "FINISH"
        } else {
            btn_submit?.text = "SUBMIT"
        }
        btn_submit?.isEnabled = false // Disable the Submit button when setting a new question
        mIsQuestionAnswered = false
        mHasUserSubmitted = false
    }

    private fun defaultOptionView() {
        val options = ArrayList<Button>()
        btn_1?.let { options.add(it) }
        btn_2?.let { options.add(it) }
        btn_3?.let { options.add(it) }
        btn_4?.let { options.add(it) }

        for (op in options) {
            op.setTextColor(Color.parseColor("#7A8089"))
            op.typeface = Typeface.DEFAULT
            op.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_border
            )
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionView()
        mSelectedOption = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_border
        )
        btn_submit?.isEnabled = true // Enable the Submit button when an option is selected
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.option_1 -> btn_1?.let {
                if (!mHasUserSubmitted) selectedOptionView(it, 1)
            }
            R.id.option_2 -> btn_2?.let {
                if (!mHasUserSubmitted) selectedOptionView(it, 2)
            }
            R.id.option_3 -> btn_3?.let {
                if (!mHasUserSubmitted) selectedOptionView(it, 3)
            }
            R.id.option_4 -> btn_4?.let {
                if (!mHasUserSubmitted) selectedOptionView(it, 4)
            }
            R.id.option_submit -> {
                if (btn_submit?.text == "GO TO NEXT QUESTION" || btn_submit?.text == "FINISH") {
                    mCurrentPosition++
                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                            mIsQuestionAnswered = false
                            mHasUserSubmitted = false
                        }
                        else -> {
                            val intent = Intent(this, Result::class.java)
                            intent.putExtra(Constants.USER_NAME, mUsername)
                            intent.putExtra(Constants.CORRECT_ANSWER, mCorrectAnswer)
                            intent.putExtra(Constants.TOTAL_QUES, mQuestionList?.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else if (mSelectedOption == 0) {
                    Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
                } else {
                    if (!mIsQuestionAnswered) {
                        mHasUserSubmitted = true
                        val question = mQuestionList?.get(mCurrentPosition - 1)
                        if (question!!.correctAns != mSelectedOption) {
                            answerView(mSelectedOption, R.drawable.wrong_answer)
                        } else {
                            mCorrectAnswer++
                        }
                        answerView(question.correctAns, R.drawable.right_answer)

                        if (mCurrentPosition == mQuestionList!!.size) {
                            btn_submit?.text = "FINISH"
                        } else {
                            btn_submit?.text = "GO TO NEXT QUESTION"
                        }
                        mIsQuestionAnswered = true
                    }
                    mSelectedOption = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawable: Int) {
        when (answer) {
            1 -> btn_1?.background = ContextCompat.getDrawable(this, drawable)
            2 -> btn_2?.background = ContextCompat.getDrawable(this, drawable)
            3 -> btn_3?.background = ContextCompat.getDrawable(this, drawable)
            4 -> btn_4?.background = ContextCompat.getDrawable(this, drawable)
        }
    }
}
