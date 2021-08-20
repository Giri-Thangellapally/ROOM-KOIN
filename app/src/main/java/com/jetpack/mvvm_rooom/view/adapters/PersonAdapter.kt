package com.jetpack.mvvm_rooom.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jetpack.mvvm_rooom.R
import com.jetpack.mvvm_rooom.databinding.PersonRowBinding
import com.jetpack.mvvm_rooom.repositories.room.PersonTable

class PersonAdapter:RecyclerView.Adapter<PersonAdapter.MyViewHolder>(){

  lateinit var personRowBinding: PersonRowBinding

     private var personsList= mutableListOf<PersonTable>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view =LayoutInflater.from(parent.context).inflate(R.layout.person_row,parent,false)
        personRowBinding= DataBindingUtil.bind(view)!!
        return MyViewHolder(personRowBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(personsList.get(position))
    }

    override fun getItemCount(): Int {

        return personsList.size
    }

    fun setPersonsData(list: List<PersonTable>){
        personsList=list.toMutableList()
        notifyDataSetChanged()
    }


    inner class MyViewHolder(private val personRowBinding: PersonRowBinding) :RecyclerView.ViewHolder(personRowBinding.root){
        fun bind(person:PersonTable){
            personRowBinding.person=person
            personRowBinding.executePendingBindings()
        }

    }

}

