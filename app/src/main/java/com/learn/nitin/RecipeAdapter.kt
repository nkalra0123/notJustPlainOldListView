package com.learn.nitin

import android.content.Context
import android.content.res.Resources
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class RecipeAdapter(private val context: Context,
                    private val dataSource: ArrayList<Recipe>) : BaseAdapter() {

    companion object {
        private val LABEL_COLOR = hashMapOf<String,Int>(
                "Low-Carb" to R.color.colorLowCarb,
                "Balanced" to R.color.colorBalanced,
                "Medium-Carb" to R.color.colorMediumCarb,
                "Low-Fat" to R.color.colorLowFat,
                "Vegetarian" to R.color.colorVegetarian,
                "Low-Sodium" to R.color.colorLowSodium
        )
    }

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * [android.view.LayoutInflater.inflate]
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position The position of the item within the adapter's data set of the item whose view
     * we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     * is non-null and of an appropriate type before using. If it is not possible to convert
     * this view to display the correct data, this method can create a new view.
     * Heterogeneous lists can specify their number of view types, so that this View is
     * always of the right type (see [.getViewTypeCount] and
     * [.getItemViewType]).
     * @param parent The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view : View
        val holder : ViewHolder

        if(convertView == null) {
             view = inflater.inflate(R.layout.list_item_recipe,parent,false)

            holder = ViewHolder()

            // Get title element
             holder.titleTextView = view.findViewById(R.id.recipe_list_title) as TextView

// Get subtitle element
             holder.subtitleTextView = view.findViewById(R.id.recipe_list_subtitle) as TextView

// Get detail element
             holder.detailTextView = view.findViewById(R.id.recipe_list_detail) as TextView

// Get thumbnail element
             holder.thumbnailImageView = view.findViewById(R.id.recipe_list_thumbnail) as ImageView

            val titleTypeFace = ResourcesCompat.getFont(context, R.font.josefinsans_bold)
            holder.titleTextView.typeface = titleTypeFace

            val subtitleTypeFace = ResourcesCompat.getFont(context, R.font.josefinsans_semibolditalic)
            holder.subtitleTextView.typeface = subtitleTypeFace

            val detailTypeFace = ResourcesCompat.getFont(context, R.font.quicksand_bold)
            holder.detailTextView.typeface = detailTypeFace

            view.tag = holder

        }
        else
        {
            view = convertView
            holder = convertView.tag as ViewHolder
        }


        // 1
        val recipe = getItem(position) as Recipe

        val titleTextView  = holder.titleTextView
        val subtitleTextView  = holder.subtitleTextView
        val detailTextView = holder.detailTextView
        val thumbnailImageView = holder.thumbnailImageView

// 2
        titleTextView.text = recipe.title
        subtitleTextView.text = recipe.description
        detailTextView.text = recipe.label

        detailTextView.setTextColor(ContextCompat.getColor(context, LABEL_COLOR[recipe.label] ?: R.color.colorPrimary));

// 3
        Picasso.with(context).load(recipe.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView)


        return view
    }

    private class ViewHolder {
        lateinit var titleTextView: TextView
        lateinit var subtitleTextView: TextView
        lateinit var detailTextView: TextView
        lateinit var thumbnailImageView: ImageView
    }



    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     * data set.
     * @return The data at the specified position.
     */
    override fun getItem(position: Int): Any {
        return dataSource.get(position)
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    override fun getItemId(position: Int): Long {
        return (position.toLong())
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    override fun getCount(): Int {
        return dataSource.size
    }

}