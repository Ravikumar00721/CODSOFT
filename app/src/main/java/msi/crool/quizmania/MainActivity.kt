package msi.crool.quizmania

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button_1:Button?=findViewById(R.id.button_1)
        val edit_text:EditText?=findViewById(R.id.textfield_1)
        button_1?.setOnClickListener{
            if(edit_text?.text?.isEmpty()==true)
            {
                Toast.makeText(this,"Pls Enter The Name",Toast.LENGTH_LONG).show()
            }
            else
            {
                val intent=Intent(this,QuizQuestion::class.java)
                intent.putExtra(Constants.USER_NAME,edit_text?.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}

