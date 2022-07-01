package com.example.controlwork2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.controlwork2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val allWords = arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSave.setOnClickListener{
            onSaveClicked()
        }
        binding.btnOutput.setOnClickListener {
            onOutputClicked()
        }
    }
    fun onSaveClicked(){
        if(binding.anagram.text.toString().isEmpty()){
            Toast.makeText(applicationContext, "Please enter anagram", Toast.LENGTH_SHORT).show()
            return
        }
        allWords.add(binding.anagram.text.toString())
        Toast.makeText(applicationContext, "String added", Toast.LENGTH_SHORT).show()
    }
    @SuppressLint("SetTextI18n")
    fun onOutputClicked(){
        val sortedAnagrams = arrayListOf<CharArray>()
        val countAnagrams = arrayListOf<ArrayList<String>>()
        var added: Boolean
        allWords.forEach{
            added = false
            val sortedCharArray = it.toCharArray().apply { sort() }
            for(i in sortedAnagrams.indices){
                if(sortedCharArray.contentEquals(sortedAnagrams[i])){
                    countAnagrams[i].add(it)
                    added = true
                    break
                }
            }
            if(!added){
                sortedAnagrams.add(sortedCharArray)
                countAnagrams.add(arrayListOf(it))
            }
        }
        binding.anagramCount.text = "Anagram count: ${countAnagrams.size}"
    }
}