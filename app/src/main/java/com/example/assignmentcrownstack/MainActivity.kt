package com.example.assignmentcrownstack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.assignmentcrownstack.repos.network.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var albumViewModel: MainViewModel

    private lateinit var adapter: AlbumAdapter

    private var promptFragment: ProgressPrompt? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!::adapter.isInitialized) {
            adapter = AlbumAdapter(this)
        }

        setAdapter()

        albumViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        albumViewModel.fetchAllAlbums().observe(this, { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    hideProcessDialog()
                    resource.data?.let {
                        adapter.setData(it)
                    }
                }
                Status.ERROR -> {
                    hideProcessDialog()
                    Toast.makeText(
                        this@MainActivity,
                        resource.message ?: "Something went wrong, Please try again",
                        Toast.LENGTH_LONG
                    ).show()
                }
                Status.LOADING -> {
                    showProcessDialog()
                }
            }
        })
    }

    private fun setAdapter() {
        rv_main.layoutManager = GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false)
        rv_main.adapter = adapter
        swipeContainer.setOnRefreshListener {
            albumViewModel.refreshData().observe(this, { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        swipeContainer.isRefreshing = false
                        resource.data?.let {
                            adapter.setData(it)
                        }
                    }
                    Status.ERROR -> {
                        swipeContainer.isRefreshing = false
                        Toast.makeText(
                            this@MainActivity,
                            resource.message ?: "Something went wrong, Please try again",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    Status.LOADING -> {
                        //showProcessDialog()
                    }
                }
            })
        }
    }

    fun showProcessDialog() {
        promptFragment = ProgressPrompt.getInstance()
        promptFragment?.show(supportFragmentManager, "ProcessDialog")
    }

    fun hideProcessDialog() {
        promptFragment?.dismiss()
    }

}