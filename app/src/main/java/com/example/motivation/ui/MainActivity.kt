package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.R
import com.example.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private var category = MotivationConstants.CATEGORY.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

       //handleFilter(R.id.image_all)

        setObserver(category)

        binding.buttonNewPhase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_new_phase) {
            setObserver(category)
        } else if (view.id in listOf(R.id.image_all, R.id.image_happy, R.id.image_sunny)) {
            //handleFilter(view.id)
            setObserver(category)
        }
    }


    private fun setObserver(category: Int) {
        viewModel.getPhrase(category).observe(this,
            Observer { binding.textPhrase.text = it })
        viewModel.getName(this).observe(this,
            Observer { binding.textUserName.text = "${getString(R.string.hello)} ${it}!" })


        viewModel.handleFilter(category, this)

    }
}