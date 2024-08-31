package msi.crool.quizmania
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class Result : AppCompatActivity(),View.OnClickListener {
    private var finish:Button?=null
    private var mUserName:String?=null
    private var mCorrectAnswer:Int?=null
    private var mTotalQuestion:Int?=null

    private var n:TextView?=null
    private var s:TextView?=null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        finish=findViewById(R.id.button_finish)
        finish?.setOnClickListener(this)

        mUserName=intent.getStringExtra(Constants.USER_NAME).toString()
        mCorrectAnswer=intent.getIntExtra(Constants.CORRECT_ANSWER,0)
        mTotalQuestion=intent.getIntExtra(Constants.TOTAL_QUES,0)

        n=findViewById(R.id.name)
        s=findViewById(R.id.scored)
        n?.text=mUserName
        s?.text="You Scored $mCorrectAnswer Out Of $mTotalQuestion"

    }

    override fun onClick(v: View?) {
        when(v?.id){
            finish?.id->{
                val ii= Intent(this,MainActivity::class.java)
                startActivity(ii)
            }
        }
    }
}