package com.example.mhawk

import android.app.Activity
import android.nfc.NfcAdapter
import android.nfc.Tag


private const val FLAGS = NfcAdapter.FLAG_READER_NFC_A or NfcAdapter.FLAG_READER_NFC_B

fun Activity.startListeningForContactLessCard(
    onCardDetected: (tag: Tag) -> Unit
) {

    val adapter = NfcAdapter.getDefaultAdapter(this)
        ?: throw Exception("Device does not support NFC!")

    if (!adapter.isEnabled)
        throw Exception("Please enable NFC to start card detection!")

    adapter.enableReaderMode(
        this,
        { if (it != null) onCardDetected(it) },
        FLAGS,
        null
    )

}

fun Activity.stopListeningForContactLessCard() {

    val adapter = NfcAdapter.getDefaultAdapter(this)
        ?: throw Exception("Device does not support NFC!")

    adapter.disableReaderMode(this)

}
