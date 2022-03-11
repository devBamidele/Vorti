package com.example.vorti.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.vorti.R
import com.example.vorti.databinding.FragmentLoginPageBinding
import com.example.vorti.model.VortiViewModel

class LoginPage : Fragment() {

    private val sharedViewModel: VortiViewModel by activityViewModels()

    private var binding: FragmentLoginPageBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginPageBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            loginPage = this@LoginPage
            rememberme.setOnClickListener { showToastMessage() }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun signup(){
        findNavController().navigate(R.id.action_loginPage_to_signinPage)
    }

    fun onLogin() {
        val eMail = binding?.input1?.text.toString()
        val password = binding?.input2?.text.toString()
        var c1 = false
        var c2 = false

        if(!sharedViewModel.confirmEmailFormat(eMail)){
            setInvalidEmailError(true)
        }else {
            setInvalidEmailError(false)
            c1 = true
        }

        if(!sharedViewModel.confirmPassword(password)){
            setInvalidPasswordError(true)
        }else{
            setInvalidPasswordError(false)
            c2 = true
        }

        /**
         * If the email and password match the verified format
         * Enter the values into the [sharedViewModel.passwordChecker()] method
         * If it returns true navigate to hte homePage
         */
        if(c1 && c2){
            if(sharedViewModel.passwordChecker(eMail, password)) {
                sharedViewModel.changer(eMail)
                setIncorrectEandP(false)
                findNavController().navigate(R.id.action_loginPage_to_HomePage)
            }else{
                setIncorrectEandP(true)
                considerSignup()
            }
        }
    }

    private fun setInvalidEmailError(error: Boolean){
        if (error) {
            binding?.EmailUsername?.isErrorEnabled = true
            binding?.EmailUsername?.error = getString(R.string.invalid_Email)
        }
        else {
            binding?.EmailUsername?.isErrorEnabled = false
        }
    }

    private fun setInvalidPasswordError(error: Boolean){
        if (error) {
            binding?.password?.isErrorEnabled = true
            binding?.password?.error = getString(R.string.invalid_Password)
        }
        else {
            binding?.password?.isErrorEnabled = false
        }
    }

    private fun setIncorrectEandP(error: Boolean){
        if(error) {
            binding?.EmailUsername?.isErrorEnabled = true
            binding?.EmailUsername?.error = getString(R.string.incorrect)
            binding?.password?.isErrorEnabled = true
            binding?.password?.error = getString(R.string.incorrect)
        }
        else {
            binding?.EmailUsername?.isErrorEnabled = false
            binding?.password?.isErrorEnabled = false
        }
    }

    private fun showToastMessage(): Boolean{
        val text = getString(R.string.remeberme)
        Toast.makeText(activity, text, Toast.LENGTH_SHORT ).show()
        return true
    }

    private fun considerSignup(): Boolean{
        val text = getString(R.string.consider)
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
        return true
    }

}