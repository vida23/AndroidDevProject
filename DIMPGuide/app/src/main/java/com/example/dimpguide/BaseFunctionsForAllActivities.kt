package com.example.dimpguide

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity


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

            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.putExtra("EXIT", true)
            startActivity(intent)
            finish()
            // Referens:  https://stackoverflow.com/questions/14001963/finish-all-activities-at-a-time
            return true
        }else {

            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.putExtra("EXIT", true)
            startActivity(intent)
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