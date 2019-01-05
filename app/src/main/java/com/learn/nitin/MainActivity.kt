
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
import android.util.Log
import java.io.File


class MainActivity : AppCompatActivity() {

    private lateinit var listView : ListView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
      Log.d("nitin","in onc create");

      listView = findViewById<ListView>(R.id.recipe_list_view)

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

    val adapter = RecipeAdapter(this,recipeList)

    listView.adapter = adapter

  }

}
