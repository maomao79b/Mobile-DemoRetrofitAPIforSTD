package com.example.demoretrofitapiforstd

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecycleViewAdapter :RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder>(){

    private var dataList:ArrayList<User> = ArrayList()
    private var OnClickItem:((User)->Unit)? = null
    private var OnClickDeleteItem:((User)->Unit)? = null

    fun setOnClickItem(callback:(User)->Unit){
        this.OnClickItem = callback
    }
    fun setOnClickDeleteItem(callback:(User)->Unit){
        this.OnClickDeleteItem = callback
    }
    fun addItems(items:ArrayList<User>) {
        this.dataList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= MyViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.items_user_list,parent,false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var users = dataList[position]
        holder.bind(users)
        holder.itemView.setOnClickListener{OnClickItem?.invoke(users)} // ตำแหน่งที่ Click
        holder.btnDelete.setOnClickListener{OnClickDeleteItem?.invoke(users)}
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
    class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        val txtName = view.findViewById<TextView>(R.id.tvName)
        val txtEmail = view.findViewById<TextView>(R.id.tvEmail)
        val txtStatus = view.findViewById<TextView>(R.id.tvStatus)
        val btnDelete = view.findViewById<Button>(R.id.btnDelete)
        fun bind(data: User){
            txtName.text = data.name
            txtEmail.text = data.email
            txtStatus.text = data.status
        }
    }
}