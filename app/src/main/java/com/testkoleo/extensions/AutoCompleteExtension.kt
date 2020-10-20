package com.testkoleo.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.testkoleo.data.models.Station

fun AutoCompleteTextView.onChangeText(onTextChange: (text: CharSequence) -> Unit) {
    this.addTextChangedListener ( object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //TODO("Not yet implemented")
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if(!p0.isNullOrEmpty()) onTextChange(p0)
        }

        override fun afterTextChanged(p0: Editable?) {
            //TODO("Not yet implemented")
        }
    })
}

fun AutoCompleteTextView.setOnclickListenerAndGetItem(): LiveData<Station?> {
    val stationLiveData = MutableLiveData<Station>()

    onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
        val tempStation = (parent?.getItemAtPosition(position) as Station)
        setText(tempStation.name)

        stationLiveData.postValue(tempStation)
    }
    return stationLiveData
}