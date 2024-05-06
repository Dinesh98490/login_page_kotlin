package com.example.loginpage

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginpage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding
    lateinit var SharedPreferences: SharedPreferences

    var email:String? = null
    var password:String? =null
    var remember: Boolean? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        var view = mainBinding.root
        setContentView(view)

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.recyclerview)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mainBinding.RegisterId.setOnClickListener {
            SharedPreferences = this.getSharedPreferences("uservalues", MODE_PRIVATE)

            email = mainBinding.emailId.text.toString()
            password = mainBinding.passwordId.text.toString()
            remember = mainBinding.rememberId.isChecked
            var editor = SharedPreferences.edit()
            editor.putString("email", email)
            editor.putString("password", password)
            editor.putBoolean("remember", remember!!)

            editor.apply()
            Toast.makeText(
                applicationContext, "Data Saved", Toast.LENGTH_LONG
            ).show()
        }

            mainBinding.loginId.setOnClickListener {
                email = SharedPreferences.getString("email","")
                password = SharedPreferences.getString("password","")
                if(email.toString()==mainBinding.emailId.text.toString() && password.toString()==mainBinding.passwordId.text.toString()){
                    var intent = Intent(this@MainActivity,RecyclerView::class.java)
                    startActivity(intent)

                }

    }
}
    override fun onPause() {
        SharedPreferences = this.getSharedPreferences("uservalues", MODE_PRIVATE)

        email = mainBinding.emailId.text.toString()
        password = mainBinding.passwordId.text.toString()
        remember = mainBinding.rememberId.isChecked

        var editor = SharedPreferences.edit()
        editor.putString("email",email)
        editor.putString("password",password)
        editor.putBoolean("remember",remember!!)

        editor.apply()
        Toast.makeText(applicationContext,"Data Saved",Toast.LENGTH_LONG
        ).show()
        super.onPause()
    }
    override fun onResume() {
        SharedPreferences = this.getSharedPreferences("uservalues", MODE_PRIVATE)
        email = SharedPreferences.getString("email","")
        password = SharedPreferences.getString("password","")
        remember = SharedPreferences.getBoolean("remember",false)
        mainBinding.emailId.setText(email)
        mainBinding.passwordId.setText(password)
        mainBinding.rememberId.isChecked = remember!!

        super.onResume()
    }
}
