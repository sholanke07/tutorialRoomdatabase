package com.lateef.tutorialroomdb.ui

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayout
import com.lateef.tutorialroomdb.R
import com.lateef.tutorialroomdb.model.User
import com.lateef.tutorialroomdb.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    // UpdatefragmentArgs is basically are class that is automati generated when we create agument for our update fragement from our navigation graph
    //the update fragment will contain our current user
    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.update_firstname.setText(args.currectUser.fristname)
        view.update_lastname.setText(args.currectUser.lastname)
        view.update_age.setText(args.currectUser.age.toString())

        view.btn_update.setOnClickListener {
            updateUser()
        }

        //add menu
        setHasOptionsMenu(true)

        return view
    }

    private fun updateUser(){
        val firstname = update_firstname.text.toString()
        val lastname = update_lastname.text.toString()
        val age = Integer.parseInt(update_age.text.toString())

        if (inputCheck(firstname, lastname, update_age.text)) {
            //create user object
            val updatedUser = User(args.currectUser.id, firstname, lastname, age)
            //update current user
            mUserViewModel.updateUser(updatedUser)

            Toast.makeText(requireContext(), "Successfully Updated", Toast.LENGTH_LONG).show()

            findNavController().popBackStack()
           // findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please Fill All Fields", Toast.LENGTH_LONG).show()
        }


        }

    private fun inputCheck(firstname: String, lastname: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstname) && TextUtils.isEmpty(lastname) && age.isEmpty())

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_menu){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            mUserViewModel.deleteUser(args.currectUser)
            Toast.makeText(requireContext(), "Successful Removed: ${args.currectUser.fristname}", Toast.LENGTH_LONG).show()
           findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){ _, _ -> }

        builder.setTitle("Delete ${args.currectUser.fristname}?")
        builder.setMessage("Are you sure you want to delete ${args.currectUser.fristname}?")
        builder.create().show()
    }


}