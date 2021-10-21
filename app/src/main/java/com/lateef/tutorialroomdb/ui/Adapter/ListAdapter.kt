package com.lateef.tutorialroomdb.ui.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lateef.tutorialroomdb.R
import com.lateef.tutorialroomdb.model.User
import com.lateef.tutorialroomdb.ui.ListFragment
import com.lateef.tutorialroomdb.ui.ListFragmentDirections
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.id_txt.text = currentItem.id.toString()
        holder.itemView.firstname_txt.text = currentItem.fristname
        holder.itemView.lastname_txt.text = currentItem.lastname
        holder.itemView.age_txt.text = currentItem.age.toString()

        holder.itemView.rowLayout.setOnClickListener {
            // We are passing our user object to our Update Fragment
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }
}