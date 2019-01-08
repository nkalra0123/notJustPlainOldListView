package com.learn.nitin

import android.content.ContentValues.TAG
import android.content.Context
import android.content.res.Resources
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class RecipeAdapter(private val context: Context,
                    private val dataSource: ArrayList<Recipe>) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {


    class ViewHolder(itemView : View,context: Context,val dataSource: ArrayList<Recipe> ) : RecyclerView.ViewHolder(itemView)
    {

        val titleTextView : TextView
        val subtitleTextView : TextView

        val  detailTextView : TextView
        val thumbnailImageView : ImageView
        init {
            // Define click listener for the ViewHolder's View.
            itemView.setOnClickListener {
                Log.d(TAG, "Element $adapterPosition clicked.")
                val detailIntent = RecipeDetailActivity.newIntent(context,dataSource.get(adapterPosition))

                context.startActivity(detailIntent)

            }
            // Get title element
            titleTextView = itemView.findViewById(R.id.recipe_list_title) as TextView

            // Get subtitle element
            subtitleTextView = itemView.findViewById(R.id.recipe_list_subtitle) as TextView

            // Get detail element
            detailTextView = itemView.findViewById(R.id.recipe_list_detail) as TextView

            // Get thumbnail element
            thumbnailImageView = itemView.findViewById(R.id.recipe_list_thumbnail) as ImageView
        }

        }




    /**
     * Called when RecyclerView needs a new [ViewHolder] of the given type to represent
     * an item.
     *
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     *
     *
     * The new ViewHolder will be used to display items of the adapter using
     * [.onBindViewHolder]. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary [View.findViewById] calls.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     * @see .getItemViewType
     * @see .onBindViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView : View = LayoutInflater.from(parent?.context ).inflate(R.layout.list_item_recipe,null)

        return ViewHolder(itemView,context,dataSource)
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return dataSource.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [ViewHolder.itemView] to reflect the item at the given
     * position.
     *
     *
     * Note that unlike [android.widget.ListView], RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the `position` parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use [ViewHolder.getAdapterPosition] which will
     * have the updated adapter position.
     *
     * Override [.onBindViewHolder] instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

       val p :Recipe = dataSource.get(position)

        holder?.titleTextView?.setText(p.title)
        holder?.subtitleTextView?.setText(p.description)
        holder?.detailTextView?.setText(p.label)

        val titleTypeFace = ResourcesCompat.getFont(context, R.font.josefinsans_bold)
        holder?.titleTextView?.typeface = titleTypeFace

        val subtitleTypeFace = ResourcesCompat.getFont(context, R.font.josefinsans_semibolditalic)
        holder?.subtitleTextView?.typeface = subtitleTypeFace

        val detailTypeFace = ResourcesCompat.getFont(context, R.font.quicksand_bold)
        holder?.detailTextView?.typeface = detailTypeFace

        holder?.detailTextView?.setTextColor(ContextCompat.getColor(context, LABEL_COLOR[p.label] ?: R.color.colorPrimary));


        Picasso.with(context).load(p.imageUrl).placeholder(R.mipmap.ic_launcher).into(holder?.thumbnailImageView)

    }

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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view : View
        val holder : ViewHolder

        if(convertView == null) {
             view = inflater.inflate(R.layout.list_item_recipe,parent,false)

            holder = ViewHolder()



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


    */



    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     * data set.
     * @return The data at the specified position.

    override fun getItem(position: Int): Any {
        return dataSource.get(position)
    }
    */

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.

    override fun getItemId(position: Int): Long {
        return (position.toLong())
    }
    */

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.

    override fun getCount(): Int {
        return dataSource.size
    }
    */

}