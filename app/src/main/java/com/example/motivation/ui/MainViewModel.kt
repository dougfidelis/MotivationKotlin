package com.example.motivation.ui

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.motivation.R
import com.example.motivation.data.MockPhrases
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.infra.SecurityPreferences

class MainViewModel() : ViewModel() {


    private var textPhrase = MutableLiveData<String>()
    private var name = MutableLiveData<String>()
    private var imageAll = MutableLiveData<String>()
    private var imageHappy = MutableLiveData<String>()
    private var imageSunny = MutableLiveData<String>()


    fun getName(context: Context): LiveData<String> {
        name.value = SecurityPreferences(context).getString(
            MotivationConstants.KEY.USER_NAME
        )
        return name
    }


    fun getPhrase(category: Int): LiveData<String> {
        textPhrase.value = MockPhrases().getPhrase(category)
        return textPhrase
    }

    fun handleFilter(id: Int, context: Context) {


        binding.imageAll.setColorFilter(
            ContextCompat.getColor(context, R.color.dark_purple)
        )
        binding.imageHappy.setColorFilter(
            ContextCompat.getColor(context, R.color.dark_purple)
        )
        binding.imageSunny.setColorFilter(
            ContextCompat.getColor(context, R.color.dark_purple)
        )

        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(
                    ContextCompat.getColor(context, R.color.white)
                )
                category = MotivationConstants.CATEGORY.ALL
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(
                    ContextCompat.getColor(context, R.color.white)
                )
                category = MotivationConstants.CATEGORY.HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(
                    ContextCompat.getColor(context, R.color.white)
                )
                category = MotivationConstants.CATEGORY.SUNNY
            }
        }

    }


}