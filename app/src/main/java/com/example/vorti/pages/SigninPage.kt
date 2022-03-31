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
import com.example.vorti.databinding.FragmentSigninPageBinding
import com.example.vorti.model.VortiViewModel

class SigninPage : Fragment() {

    private val sharedViewModel: VortiViewModel by activityViewModels()

    private var binding: FragmentSigninPageBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSigninPageBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            signinPage = this@SigninPage

        }

    }

    /**
     * When the user clicks the "Already have an account button"
     */
    fun login(){
        findNavController().navigate(R.id.action_signinPage_to_loginPage)
    }

    /**
     * Navigation to the home page
     */
    fun homePage(){
        if(onSignUp()){
            findNavController().navigate(R.id.action_signinPage_to_HomePage)
        } else {
            completeProcess()
        }

    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun onSignUp(): Boolean{
        val fullName = binding?.input1?.text.toString()
        val eMail = binding?.input2?.text.toString()
        val password = binding?.input3?.text.toString()
        val conPassword = binding?.input4?.text.toString()
        var c1 = false



        if(!sharedViewModel.confirmName(fullName)){
            setInvalidNameError(true)
        } else {
            setInvalidNameError(false)
            sharedViewModel.setName(fullName)
            c1 = true

        }
        return c1
    }

    private fun completeProcess(): Boolean{
        val text = getString(R.string.complete)
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
        return true
    }

    private fun setInvalidNameError(error: Boolean){
        if (error) {
            binding?.fullname?.isErrorEnabled = error
            binding?.fullname?.error = getString(R.string.invalid_name)
        }
        else {
            binding?.fullname?.isErrorEnabled = false
        }
    }

    private fun setInvalidEmailError(error: Boolean){
        if (error) {
            binding?.email?.isErrorEnabled = true
            binding?.email?.error = getString(R.string.invalid_Email)
        }
        else {
            binding?.email?.isErrorEnabled = false
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

    private fun setUnMatchingPassword(error: Boolean){
        if(error){
            binding?.passwordAgain?.isErrorEnabled = true
            binding?.passwordAgain?.error = getString(R.string.unmatchingPassword)
        }
        else {
            binding?.passwordAgain?.isErrorEnabled = false
        }
    }
}