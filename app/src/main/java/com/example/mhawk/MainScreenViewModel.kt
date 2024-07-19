package com.example.mhawk

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.nfc.Tag
import android.nfc.tech.IsoDep
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import cafe.adriel.voyager.core.model.ScreenModel

@OptIn(ExperimentalStdlibApi::class)
class MainScreenViewModel : ScreenModel {

    var input by mutableStateOf(TextFieldValue())
    var output by mutableStateOf("Copy From Side Icon")

    init {
      try {
          MainActivity.activity?.startListeningForContactLessCard {
              input = TextFieldValue(it.id.toHexString().uppercase())
          }
      }catch (e:Exception){
          Toast.makeText(App.instance,e.message,Toast.LENGTH_LONG).show()
      }

    }

    fun hexToDecimal(){
        val hexValue = input.text.replace(":","")
        output = hexValue.toLong(16).toString()

    }


    fun copy() {
        val clipboardManager = App.instance?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Hex",output)
        clipboardManager.setPrimaryClip(clip)
    }

    override fun onDispose() {
        super.onDispose()
        MainActivity.activity?.stopListeningForContactLessCard()
    }

}