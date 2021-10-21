package com.lateef.tutorialroomdb.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lateef.tutorialroomdb.R
import com.lateef.tutorialroomdb.viewModel.UserViewModel
import com.lateef.tutorialroomdb.ui.Adapter.ListAdapter
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // val view = inflater.inflate(R.layout.fragment_list, container, false)
        return inflater.inflate(R.layout.fragment_list, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)


            //RecyclerView
        val adapter = ListAdapter()
        val recyclerView = view.recycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //viewmodel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user->
            adapter.setData(user)

        })

        fab_add.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)

        }
        //add menu
        setHasOptionsMenu(true)


        //return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_menu){
            deleteAllUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUser(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            mUserViewModel.deleteAllUser()
            Toast.makeText(requireContext(), "Successful Removed Everything", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No"){ _, _ -> }

        builder.setTitle("Delete Everything?")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()
    }


}