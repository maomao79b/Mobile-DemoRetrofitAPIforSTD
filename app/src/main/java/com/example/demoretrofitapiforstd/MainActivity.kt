package com.example.demoretrofitapiforstd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var adapter:RecycleViewAdapter? = null
    private lateinit var recyclerView: RecyclerView
    lateinit var viewModel: MainActivityViewModel
    lateinit var viewModelUpdate:CreateNewUserViewModel
    private lateinit var btnSearch:Button
    private lateinit var btnUpdate:Button
    private lateinit var btnCreate:Button
    private lateinit var editName: EditText
    private lateinit var editEmail: EditText
    private lateinit var searchEditText: EditText
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initRecyclerView()
        initViewModel()
        initViewModelUpdate()
        btnSearch.setOnClickListener {
            if(!TextUtils.isEmpty(searchEditText.text.toString())){
                viewModel.searchUser(searchEditText.text.toString())
            }else{
                viewModel.getUserList()
            }
        }
        btnCreate.setOnClickListener {
            startActivity(Intent(this@MainActivity, CreateNewUserActivity::class.java))
        }
        // คลิกหน้าจอที่ items แล้วขึ้นที่ edittext
        adapter?.setOnClickItem {
            editName.setText(it.name)
            editEmail.setText(it.email)
            user = it
        }
        btnUpdate.setOnClickListener {
            updateUser()
            viewModel.getUserList()
        }
        adapter?.setOnClickDeleteItem {
            user = it
            deleteUser(user?.id.toString())
            viewModel.getUserList()
        }
    }
    fun deleteUser(id:String){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to delete?")
        builder.setCancelable(true)
        builder.setPositiveButton("Yes"){dialog, _->
            viewModelUpdate.deleteUser(id)
            dialog.dismiss()
        }
        builder.setNegativeButton("No"){dialog, _->
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
    }
    fun updateUser(){
        val name = editName.text.toString()
        val email = editEmail.text.toString()
        val id:String = user?.id.toString()
        if(name == user?.name && email==user?.email){
            Toast.makeText(this, "Record not Change", Toast.LENGTH_LONG,).show()
            return
        }
        if(user == null)return

        val userEdit = User(id=user!!.id, name=name, email=email, status = user?.status, gender = user?.gender)
        viewModelUpdate.updateUser(user!!.id.toString(), userEdit)
    }
    fun initViewModel(){
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getUserList()
        viewModel.recycleListData.observe(this, {adapter?.addItems(it)})
    }
    fun initViewModelUpdate(){
        viewModelUpdate = ViewModelProvider(this).get(CreateNewUserViewModel::class.java)
        viewModelUpdate.getUpdateUserObserver().observe(this, Observer<UserResponse?>{
            if(it==null){
                Toast.makeText(this@MainActivity,"Failed to update user", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this@MainActivity,"Success to update user", Toast.LENGTH_LONG).show()
            }

        })
    }
    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RecycleViewAdapter()
        recyclerView.adapter = adapter
    }
    private fun initView(){
        recyclerView = findViewById(R.id.recyclerView)
        btnSearch = findViewById(R.id.btnSearch)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnCreate = findViewById(R.id.btnCreate)
        editName = findViewById(R.id.editName)
        editEmail = findViewById(R.id.editEmail)
        searchEditText = findViewById(R.id.searchEditText)
    }
}