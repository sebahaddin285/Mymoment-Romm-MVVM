package com.marangoz.mymoments.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.marangoz.mymoments.R
import com.marangoz.mymoments.adapters.MomentAdapter
import com.marangoz.mymoments.data.MomentDao
import com.marangoz.mymoments.data.MomentDataBase
import com.marangoz.mymoments.databinding.ActivityMainBinding
import com.marangoz.mymoments.models.Momets
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter: MomentAdapter by lazy { MomentAdapter(this,viewModel) }
    private val db: MomentDataBase by lazy { MomentDataBase.accsessDatabase(this)!! }
    private val mDao: MomentDao by lazy { db.getMomentDao() }

    private val viewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(mDao)).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.rv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
        binding.floatingActionButton.setOnClickListener(){
            val text = binding.mainMomentText.text.toString()
            if (text.isEmpty()){
                Toast.makeText(this,"Empty Text",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val moment = Momets(text)
            viewModel.insertNotes(moment)

        }

        viewModel.notes.observe(this){ data->
            adapter.setList(data)
        }

        viewModel.getNotes()

    }

}