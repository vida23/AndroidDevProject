package com.example.dimpguide

import android.content.Context
import android.content.Intent
import android.provider.Settings.Global.getString
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.content.IntentCompat
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



open class BaseFunctionsForAllActivities() : AppCompatActivity(){

    var menuItem: MenuItem? = null
    fun changeMenuItemDependingOnLoginState(item:MenuItem):Boolean{
        if(LoggedInManager.isLoggedIn){
            item.title = getString(R.string.sign_out)
            return true

        }else{
            item.title = getString(R.string.sign_up_or_sign_in)
            return true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu,menu)
        menuItem = menu!!.getItem(FIRST_VALUE)
        return changeMenuItemDependingOnLoginState(menuItem!!)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.title == getString(R.string.sign_out)){
            LoggedInManager.changeLoginState(false)
            menuItem!!.title = getString(R.string.sign_up_or_sign_in)
            val intents = Intent(this, MainActivity::class.java)
            intents.addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK
                        or Intent.FLAG_ACTIVITY_CLEAR_TOP
                        or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intents)
            finish()
            return true
        }else {
            val intents = Intent(this, MainActivity::class.java)
            intents.addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK
                        or Intent.FLAG_ACTIVITY_CLEAR_TOP
                        or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intents)
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        if(LoggedInManager.isLoggedIn == true && menuItem != null){
            menuItem!!.title = getString(R.string.sign_out)
        }else if(menuItem!= null){
            menuItem!!.title = getString(R.string.sign_up_or_sign_in)
        }

    }

    companion object{
        const val FIRST_VALUE  = 0
    }
}