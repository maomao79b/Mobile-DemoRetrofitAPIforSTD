package com.example.demoretrofitapiforstd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class CreateNewUserActivity : AppCompatActivity() {
    private lateinit var editTextName:EditText
    private lateinit var editTextEmail:EditText
    private lateinit var btnInsert:Button
    private lateinit var viewModel2: CreateNewUserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_user)
        initView()
        initViewModel()
        this.btnInsert.setOnClickListener {
            createUser()
            startActivity(Intent(this@CreateNewUserActivity, MainActivity::class.java)) // สลับหน้า
        }
    }
    private fun createUser() {
        val user = User("", editTextName.text.toString(), editTextEmail.text.toString(), "Active", "Male")
        viewModel2.createNewUser(user)
    }
    private fun initView(){
        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        this.btnInsert = findViewById(R.id.btnInsert)
    }
    private fun initViewModel(){
        viewModel2 = ViewModelProvider(this).get(CreateNewUserViewModel::class.java)
        viewModel2.getCreateNewUserObserver().observe(this, {
            if(it == null){
                Toast.makeText(this@CreateNewUserActivity, "Failed to create new user", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this@CreateNewUserActivity, "Success to create user", Toast.LENGTH_LONG).show()
            }
        })
    }
}