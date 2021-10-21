package com.lateef.tutorialroomdb.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lateef.tutorialroomdb.R
import com.lateef.tutorialroomdb.model.User
import com.lateef.tutorialroomdb.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.btn_save.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val firstname = edit_firstname.text.toString()
        val lastname = edit_lastname.text.toString()
        val age = edit_age.text

        if (inputCheck(firstname, lastname, age)) {
            //create user object
            val user = User(0, firstname, lastname, Integer.parseInt(age.toString()))

            //add user to database
            mUserViewModel.addUser(user)

            Toast.makeText(requireContext(), "successfully added", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "please fill out all fields", Toast.LENGTH_LONG).show()
        }


    }

    private fun inputCheck(firstname: String, lastname: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstname) && TextUtils.isEmpty(lastname) && age.isEmpty())

    }


}