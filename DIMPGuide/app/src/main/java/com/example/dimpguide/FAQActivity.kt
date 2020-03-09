package com.example.dimpguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dimpguide.DbHandler.Companion.db

class FAQActivity : AppCompatActivity() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager : RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)

        val dataset: MutableList<FAQ> = ArrayList()

        db.collection("FAQ")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents){
                    if(document.getString("answer")!= null) {
                        val faq = FAQ(
                            document.getString("question").toString(),
                            document.getString("answer").toString()
                        )
                        dataset.add(faq)
                    }
                }

                viewManager = LinearLayoutManager(this)
                viewAdapter = FAQRecyclerViewAdapter(dataset,this)


                findViewById<RecyclerView>(R.id.FAQRecyclerView).apply{
                    setHasFixedSize(true)

                    layoutManager = viewManager

                    adapter = viewAdapter
                }

            }
            .addOnFailureListener {exception ->
                Log.w("Cannot find file", "Error getting document", exception)

            }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(LoggedInManager.isLoggedIn){
            menuInflater.inflate(R.menu.app_bar_menu,menu)
            return true

        }else
            return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.title == getString(R.string.sign_out)){
            LoggedInManager.changeLoginState(false)
            item.title = getString(R.string.sign_in)
            return true
        }else if(item.title == getString(R.string.sign_in)){
            startActivity(Intent(this,SignInActivity::class.java))
            return true
        }
        return false
    }
}
