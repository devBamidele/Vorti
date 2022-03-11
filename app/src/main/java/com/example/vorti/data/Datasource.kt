package com.example.vorti.data

import com.example.vorti.model.Values

class Datasource {
    fun loadValues(): List<Values>{
        return listOf(
            // Values(Username, Email , Password, remember me)
            Values("Bamidele","bamideledavid.ajewole@gmail.com", "password", true),
            Values("David","Bamideledavid.femi@gmail.com", "12345", true),
            Values("Moses","testemail.vorti@gmail.com", "vorti", false)
        )
    }
}