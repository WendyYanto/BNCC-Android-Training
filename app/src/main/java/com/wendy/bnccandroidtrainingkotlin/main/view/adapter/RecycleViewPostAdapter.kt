package com.wendy.bnccandroidtrainingkotlin.main.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.wendy.bnccandroidtrainingkotlin.R
import com.wendy.bnccandroidtrainingkotlin.main.model.PostUiModel

class RecycleViewPostAdapter(private var items: List<PostUiModel>) :
  RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
    return PostViewHolder(view)
  }

  override fun getItemCount(): Int {
    return items.size
  }

  fun submitLists(newItems: List<PostUiModel>) {
    items = newItems
    notifyDataSetChanged()
  }

  override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, index: Int) {
    val postUiModel = items[index]
    (viewHolder as PostViewHolder).bind(postUiModel)
  }

  inner class PostViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private var textViewTitle: TextView? = null
    private var textViewBody: TextView? = null

    fun bind(postUiModel: PostUiModel) {
      textViewTitle = view.findViewById(R.id.tv_title)
      textViewBody = view.findViewById(R.id.tv_body)
      textViewTitle?.text = postUiModel.title
      textViewBody?.text = postUiModel.body
    }

  }

}