package com.example.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.R
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityUserBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.saveButton.setOnClickListener(this)

        getName()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.save_button) {
            handleSave()
        }
    }

    private fun handleSave() {
        val name = binding.editYourName.text.toString()
        if (name != "") {
            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(
                this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun getName() {
        if (SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME) != "") {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}