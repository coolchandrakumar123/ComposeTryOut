package com.chan.composetryout.compose

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.RelocationRequester
import androidx.compose.ui.layout.relocationRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by chandra-1765$ on 05/08/21$.
 */

@Composable
fun SimpleTwoItemWithObserver(observer: (MutableLiveData<String>) -> Unit, onClick: () -> Unit) {
    Column {
        //var valueText = "Chan"
        SingleButton(onClick)
        SingleTextWithObserver(observer)
    }
}

@Composable
fun SingleTextWithObserver(observer: (MutableLiveData<String>) -> Unit) {
    val valueObserver = MutableLiveData<String>()
    observer(valueObserver)
    var valueText by remember { mutableStateOf("Chan") }
    valueObserver.observe(LocalLifecycleOwner.current) {
        valueText = it
    }
    if(valueText.isNotEmpty()) {
        SingleText(valueText)
    }
}

@Composable
fun SingleText(valueText: String) {
    Text(
        text = valueText,
        style = TextStyle(fontSize = 20.sp),
        modifier = Modifier
            .testTag("singleText")
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Preview
@Composable
fun SimpleTwoItems() {
    Column {
        var valueText by remember { mutableStateOf("Chan") }
        //var valueText = "Chan"
        val context = LocalContext.current
        SingleButton {
            Toast.makeText(context, "Item Clicked", Toast.LENGTH_SHORT).show()
            valueText += "Clicked"
        }
        if (valueText.isNotEmpty()) {
            SingleText(valueText)
        }
    }
}

@Preview
@Composable
fun SimpleTwoItemsWithToast() {
    Column {
        var valueText by remember { mutableStateOf("Chan") }
        //var valueText = "Chan"
        val context = LocalContext.current
        var showPopup by remember { mutableStateOf(false) }
        SingleButton {
            Toast.makeText(context, "Item Clicked", Toast.LENGTH_SHORT).show()
            valueText += "Clicked"
            showPopup = !showPopup
        }
        Box {
            if (valueText.isNotEmpty()) {
                SingleText(valueText)
            }
            if (showPopup) {
                val cornerSize = 4.dp
                Popup(
                    alignment = Alignment.TopCenter,
                    onDismissRequest = {
                        showPopup = false
                    }) {
                    // Draw a rectangle shape with rounded corners inside the popup
                    Box(
                        Modifier
                            .background(Color.Black, RoundedCornerShape(cornerSize))
                            .padding(all = 4.dp)
                    ) {
                        Text(text = "Toast Content", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun SingleButton(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Click",
            style = TextStyle(fontSize = 20.sp, color = Color.White),
            modifier = Modifier
                .background(color = Color.Blue, shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
                .clickable {
                    Log.d("ChanLog", "SingleButton: Clicked")
                    onClick()
                }
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SingleEditText() {

    Column(modifier = Modifier
        .padding(all = 16.dp)
        .fillMaxWidth()
        .verticalScroll(
            rememberScrollState()
        )) {
        val placeHolderData = "EditText Place Holder LengthLine check to avoid nextLines..."
        SimpleBasicTextField(placeHolderData = placeHolderData)
        Spacer(modifier = Modifier.height(height = 16.dp))
        Row {
            SimpleBasicTextField()
            SimpleBasicTextField()
        }
        Spacer(modifier = Modifier.height(height = 16.dp))
        SimpleTextField(placeHolderData)
        Spacer(modifier = Modifier.height(height = 16.dp))
        SimpleOutlineTextField()
        Spacer(modifier = Modifier.height(height = 16.dp))
        Row {
            SimpleTextField()
            SimpleTextField()
        }
        Spacer(modifier = Modifier.height(height = 16.dp))
        SimpleMaterialOutlineTextField()
        Spacer(modifier = Modifier.height(height = 16.dp))
        SimpleBasicTextField(singleLine = false)
        Spacer(modifier = Modifier.height(height = 16.dp))
    }
}

@DelicateCoroutinesApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SimpleBasicTextField(placeHolderData: String = "", singleLine: Boolean = true) {
    val (text, setText) = remember { mutableStateOf("") }
    val relocationRequester = RelocationRequester()
    BasicTextField(
        value = text,
        onValueChange = setText,
        maxLines = 1,
        singleLine = singleLine,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Blue, shape = RoundedCornerShape(8.dp))
            .border(width = 2.dp, color = Color.Red, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .relocationRequester(relocationRequester)
            .onFocusEvent {
                if (it.isFocused) {
                    GlobalScope.launch {
                        delay(200)
                        relocationRequester.bringIntoView()
                    }
                }
            },
        textStyle = TextStyle(fontSize = 20.sp, color = Color.White),
        decorationBox = {
            if(placeHolderData.isNotEmpty() && text.isEmpty()) {
                Text(text = placeHolderData, style = TextStyle(fontSize = 20.sp, color = Color.White.copy(alpha = 0.42f)))
            }
            it()
        }
    )
}

@Composable
fun SimpleTextField(placeHolderData: String = "EditText") {
    val (text, setText) = remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .wrapContentWidth()
            .requiredHeight(height = 100.dp)
            .border(width = 2.dp, color = Color.Red, shape = RoundedCornerShape(8.dp))
            .background(color = Color.Blue, shape = RoundedCornerShape(8.dp)),
        value = text,
        maxLines = 5,
        onValueChange = setText,
        colors = TextFieldDefaults.textFieldColors(
            placeholderColor = Color.White.copy(alpha = 0.42f),
            textColor = Color.White,
            backgroundColor = Color.Transparent
        ),
        placeholder = {
            Text(text = placeHolderData)
        }
    )
}

@Composable
fun SimpleOutlineTextField() {
    val (text, setText) = remember { mutableStateOf("") }
    OutlinedTextField(
        modifier = Modifier
            .border(width = 2.dp, color = Color.Red, shape = RoundedCornerShape(8.dp))
            .background(color = Color.Blue, shape = RoundedCornerShape(8.dp)),
        value = text,
        onValueChange = setText,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            placeholderColor = Color.White.copy(alpha = 0.42f),
            textColor = Color.White
        ),
        placeholder = {
            Text(text = "EditText")
        }
    )
}

@Composable
fun SimpleMaterialOutlineTextField() {
    val (text, setText) = remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = setText,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Red,
            unfocusedBorderColor = Color.Red,
            placeholderColor = Color.Black.copy(alpha = 0.22f),
            textColor = Color.Black
        ),
        label = {
            Text(text = "EditText", color = Color.Black.copy(alpha = 0.5f))
        }
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SingleCheckBox() {
    /*Text(
        text = valueText,
        style = TextStyle(fontSize = 20.sp),
        modifier = Modifier
            .testTag("singleText")
            .fillMaxWidth()
            .padding(16.dp)
    )*/
    val modifier = Modifier
        .padding(16.dp)
    Column {
        val (checked, setChecked) = remember { mutableStateOf(false) }
        Checkbox(
            checked = checked,
            onCheckedChange = setChecked,
            modifier = modifier,
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Black,
                checkmarkColor = Color.Black
            )
        )
        val selected = remember { mutableStateOf(false) }

        RadioButton(
            selected = selected.value,
            onClick = {
                selected.value = !selected.value
            },
            modifier = modifier
        )
        val (switchChecked, setSwitchChecked) = remember { mutableStateOf(false) }
        Switch(
            checked = switchChecked,
            onCheckedChange = setSwitchChecked,
            modifier = modifier
        )
    }
}