package com.example.vorti.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vorti.data.Datasource

class VortiViewModel: ViewModel() {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password1 = MutableLiveData<String>()
    val password1: LiveData<String> = _password1

    private val _remember = MutableLiveData<Boolean>()
    val remember: LiveData<Boolean> = _remember

    // Load data from the dataset
    private val myDataset = Datasource().loadValues()

    private var state : Boolean = false


    fun setName(name: String){
        _name.value = name
    }

    fun setEmail(email: String){
        _email.value = email
    }

    fun setPassword(password: String){
        _password1.value = password
    }

    fun setRemember(check: Boolean){
        _remember.value = check
    }

    fun whenClick(){
        state = state != true

        when {
            state -> {
                _remember.value = true
            }
            else -> {
                _remember.value = false
            }
        }
    }


    /**
     * Check if the name field has been filled
     * And if it meets some certain criteria
     */
    fun confirmName(name: String?): Boolean{
        return name != null
                && name.isNotBlank()
                && name.length >= 4
                && name.length <= 30
                && !name.contains("[!\"#$%&'()*+,-/:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())
    }



    /**
     * Check if the email follows the correct format ("username@gmail.com")
     * And if it meets the criteria for a valid email
     */
    fun confirmEmailFormat (email : String?): Boolean {
        return email != null
                && email.isNotBlank()
                && email.contains("@")
                && email.contains(".")
                && email.length >= 12
    }


    /**
     * Check if the password field has been filled
     * And if the password meets the criteria
     */
    fun confirmPassword(pass1: String?): Boolean{
        return pass1 != null
                && pass1.length >= 5
                && pass1.isNotBlank()
    }


    /**
     * Check if the passwords are similar
     */
    fun validatePassword(pass1: String?, pass2: String?): Boolean {
        return pass1 != null && pass2 != null && pass1 == pass2
    }



    /**
     * Check if the email and password match
     * are already registered
     */
    fun passwordChecker(email: String, password: String): Boolean{
        /**
         * Create a mutable map of email and password
         */
        val accounts = mutableMapOf<String, String>()

        /**
         * Get the emails and their corresponding passwords and add them to the account
         */
        for(i in myDataset.indices){
            accounts[myDataset[i].Email] = myDataset[i].Password
        }

        // Check if the map contains the specified email
        if (accounts.containsKey(email)) {
            if (accounts.getValue(email) == password) {
                return true
            }
        }
        return false
    }

    /**
     * The constructor
     */
    init {
        resetValues()
    }

    private fun resetValues(){
        _name.value = ""
        _email.value = ""
        _password1.value = ""
        _remember.value = state
    }

    /**
     * Set the values of each individual user depending on the email entered
     */
    fun changer(value: String){
        for(i in myDataset.indices){
            when(value) {
                myDataset[i].Email -> {
                    _name.value = myDataset[i].Name
                    _email.value = myDataset[i].Email
                    _password1.value = myDataset[i].Password
                    _remember.value = myDataset[i].rem
                }
            }
        }
    }

    fun rememberDisplay(): String {
        return if(remember.value == true){
            "You asked us to remember you"
        }else{
            "You did not ask us to remember you"
        }
    }

    fun checked(): Boolean {
        return remember.value == true
    }

}