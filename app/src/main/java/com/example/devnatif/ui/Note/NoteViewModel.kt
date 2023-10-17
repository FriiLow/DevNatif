package com.example.devnatif.ui.Note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoteViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Note no no te te"
    }
    val text: LiveData<String> = _text
}