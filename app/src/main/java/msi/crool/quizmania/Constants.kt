package msi.crool.quizmania

object Constants {

    const val USER_NAME:String="user_name"
    const val TOTAL_QUES:String="total_ques"
    const val CORRECT_ANSWER:String="correct_ans"

    fun getQuestion(): ArrayList<Question> {
        val questionList=ArrayList<Question>()
        val que1=Question(
            1,
             R.drawable.ic_flag_of_argentina,
            "Which Country is this ?",
            "Argentina","India","Pakistan","Nepal",1
            )
        questionList.add(que1)
        val que2=Question(
            2,
            R.drawable.ic_flag_of_belgium,
            "Which Country is this ?",
            "Argentina","Mexico","Pakistan","Nepal",2
        )
        questionList.add(que2)
        val que3=Question(
            3,
            R.drawable.ic_flag_of_australia,
            "Which Country is this ?",
            "Argentina","India","England","Nepal",3
        )
        questionList.add(que3)
        val que4=Question(
            4,
            R.drawable.ic_flag_of_india,
            "Which Country is this ?",
            "Argentina","India","Pakistan","Nepal",2
        )
        questionList.add(que4)
        val que5=Question(
            5,
            R.drawable.ic_flag_of_denmark,
            "Which Country is this ?",
            "Argentina","India","Sweden","Nepal",3
        )
        questionList.add(que5)
        val que6=Question(
            6,
            R.drawable.ic_flag_of_fiji,
            "Which Country is this ?",
            "Lilly","India","Pakistan","Nepal",1
        )
        questionList.add(que6)

        return questionList
    }

}