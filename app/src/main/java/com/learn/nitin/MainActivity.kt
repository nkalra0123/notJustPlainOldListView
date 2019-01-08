
package com.learn.nitin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.content.Intent
import android.net.Uri
import android.os.Environment
import java.io.File.separator
import android.os.Environment.getExternalStorageDirectory
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity() {

    private lateinit var listView : ListView

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager : RecyclerView.LayoutManager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.recyclerview)
      Log.d("nitin","in onc create");

     // listView = findViewById<ListView>(R.id.recipe_list_view)

    /*
      var recipeList = Recipe.getRecipesFromFile("recipes.json",this)


      var list = ArrayList<String>(recipeList.size)

      for(i in 0 until recipeList.size){
          list.add(recipeList.get(i).title)
      }

      val adapter = ArrayAdapter(this,R.layout.simple_list_item,list)


      listView.adapter = adapter
      */

    var recipeList = Recipe.getRecipesFromFile("recipes.json",this)

    val myadapter = RecipeAdapter(this,recipeList)

    //listView.adapter = adapter

      val context = this

      /*
      listView.setOnItemClickListener { _, _, position, _ ->
          // 1
          val selectedRecipe = recipeList[position]

          // 2
          val detailIntent = RecipeDetailActivity.newIntent(context, selectedRecipe)

          // 3
          startActivity(detailIntent)
      }
      */

      viewManager = LinearLayoutManager(this)
      viewAdapter = myadapter

      recyclerView = findViewById(R.id.recyclerview)
      recyclerView.apply {
          layoutManager = viewManager

          setHasFixedSize(true)
          adapter = myadapter

      }

  }

}
