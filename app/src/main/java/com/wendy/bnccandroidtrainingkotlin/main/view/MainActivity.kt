package com.wendy.bnccandroidtrainingkotlin.main.view

import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import com.wendy.bnccandroidtrainingkotlin.result.OtherActivity
import com.wendy.bnccandroidtrainingkotlin.R
import com.wendy.bnccandroidtrainingkotlin.core.AppController
import com.wendy.bnccandroidtrainingkotlin.main.model.PostUiModel
import com.wendy.bnccandroidtrainingkotlin.main.presenter.MainPresenter
import com.wendy.bnccandroidtrainingkotlin.main.presenter.MainPresenterImpl
import com.wendy.bnccandroidtrainingkotlin.main.view.adapter.RecycleViewPostAdapter

class MainActivity : AppCompatActivity(), MainView {

  private lateinit var mButton: Button
  private var presenter: MainPresenter? = null
  private lateinit var toolbar: Toolbar
  private var progressDialog: AlertDialog? = null
  private lateinit var recycleView: RecyclerView
  private var postAdapter: RecycleViewPostAdapter? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setUp()

    mButton = findViewById(R.id.btn_click)
    mButton.setOnClickListener {
      presenter?.getPosts()
    }

    toolbar = findViewById(R.id.toolbar)
    recycleView = findViewById(R.id.rv_post)

    setSupportActionBar(toolbar)
  }

  override fun showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter?.detach()
    presenter = null
  }

  override fun onPause() {
    super.onPause()
    presenter?.resetCounter()
  }

  override fun goToOtherActivity(content: String) {
    val intent = Intent(this, OtherActivity::class.java)
    intent.putExtra(OtherActivity.CONTENT, content)
    startActivity(intent)
  }

  private fun setUp() {
    val postService =
      AppController.providePostService()
    presenter =
      MainPresenterImpl(this, postService)
  }

  override fun showLoadingDialog() {
    progressDialog?.let {
      if (!it.isShowing) {
        it.show()
      }
    } ?: run {
      progressDialog =
        AlertDialog.Builder(this).setView(ProgressBar(this)).create()
      progressDialog?.show()
    }
  }

  override fun dismissLoadingDialog() {
    progressDialog?.dismiss()
  }

  override fun populatePosts(lists: List<PostUiModel>) {
    postAdapter?.submitLists(lists) ?: run {
      postAdapter = RecycleViewPostAdapter(lists)
      recycleView.layoutManager = LinearLayoutManager(this)
      recycleView.adapter = postAdapter
    }
  }
}
