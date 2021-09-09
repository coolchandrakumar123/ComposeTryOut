package com.chan.composetryout

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.chan.composetryout.compose.*

class ComposeMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setComposeView()
    }

    private fun setComposeView() {
        setContent {
            //SimpleTwoItems()
            /*SimpleTwoItemWithObserver(
                onClick = {
                    liveData.postValue("${liveData.value},Clicked")
                },
                observer = {
                    liveData = it
                }
            )*/
            //RowWithThreeItem()
            //RowColumnThreeItem()
            //SimpleLazyColumnDemo()
            //SingleEditText()
            //SingleCheckBox()
            //SimpleLazyColumnList()
            //NestedScrollList()
            SimpleFlowRowList()
        }
    }

}