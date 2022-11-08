package br.edu.infnet.tp1_desenvolvimentokotlinandroid.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.R

fun Fragment.nav(value: Int){
    findNavController().navigate(value)
}

fun Fragment.navUp(){
    findNavController().navigateUp()
}

fun AppCompatActivity.getSharedPrefs(): SharedPreferences {
    return getSharedPreferences(
        getString(R.string.my_preference_name),
        Context.MODE_PRIVATE
    )
}

fun AppCompatActivity.getNameFromSharedPrefs(): String {
    val sharedPrefs = getSharedPrefs()
    return sharedPrefs.getString(
        getString(R.string.name_key),
        ""
    ) ?: ""
}

fun AppCompatActivity.saveNameToSharedPrefs(value: String) {
    val sharedPrefs = getSharedPrefs()
    val editor = sharedPrefs.edit()
    editor.putString(
        getString(R.string.name_key),
        value
    )
    editor.apply()
}