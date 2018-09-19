package com.itbenevides.catalogo

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import android.view.View.*
import android.widget.TextView
import com.eyalbira.loadingdots.LoadingDots
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), DataMovieReturnInterface, OnClickInterface {

    lateinit var recyclerView: RecyclerView
    lateinit var textviewWellcome: TextView
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var layoutManager: LinearLayoutManager

    var loadingDots: LoadingDots? = null
    var movies: MutableList<Movie>? = null
    var mLoading = false
    var query: String = ""
    var page: Int = 1
    var totalItem: Long = 0;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadViews()


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)


        loadSearch(menu)


        return true
    }

    override fun onClick(var1: View, movie: Movie) {
        if (!checkConnection(applicationContext)) showAlertSnackbarConnection()

        DetailMovieSearchAsynk(applicationContext, this@MainActivity, movie).execute()


    }

    override fun dataDetailMovieReturn(detail: Detail?) {


        if(detail!=null){
            val intent = Intent(applicationContext, DetailActivity::class.java)
            intent.putExtra("movie", detail)
            startActivity(intent)
        }else{
            showAlertDetailEmptyResult()
        }



    }

    override fun dataMovieReturn(list: MutableList<Movie>?, totalItem: Long) {


        hideProgressDots()

        if (totalItem != 0L)
            this.totalItem = totalItem

        mLoading = false
        if (list != null && !list.isEmpty()) {
            loadRecyclerViewData(list)
        } else {
            showTextViewWellcome()
            showAlertEmptyResult()
            hideRecyclerView()

        }

        if (!checkConnection(applicationContext)) showAlertSnackbarConnection()

    }

    private fun loadRecyclerViewData(list: MutableList<Movie>?) {


        if (page == 1) {
            movies = list
            layoutManager = LinearLayoutManager(applicationContext)
            recyclerView.adapter = MovieAdapter(movies!!, applicationContext,this)
            recyclerView.layoutManager = layoutManager
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val visibleItemCount = layoutManager.childCount;
                    val totalIt = layoutManager.itemCount
                    val lastVisibleItem = layoutManager.findFirstVisibleItemPosition()

                    if (!mLoading && (lastVisibleItem + visibleItemCount) >= totalIt - 1 && totalIt < totalItem) {
                        mLoading = true
                        page++
                        MovieSearchAsynk(query, page, applicationContext, this@MainActivity, movies!!).execute()

                    }
                }
            })
        } else {
            if (list != null)
                movies?.addAll(list)

            recyclerView.adapter?.notifyDataSetChanged()

        }

        showRecyclerView()


    }


    private fun loadSearch(menu: Menu) {

        val search = menu.findItem(R.id.search)
        val searchView = search.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(qry: String): Boolean {
                page = 1
                query = qry
                hideTextViewWellcome()
                clearRecyclerView()
                showProgressDots()

                MovieSearchAsynk(query, page, applicationContext, this@MainActivity, ArrayList()).execute()

                return false
            }


            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })


        searchView.addOnAttachStateChangeListener(object : OnAttachStateChangeListener {

            override fun onViewDetachedFromWindow(arg0: View) {
                clearRecyclerView()
                showTextViewWellcome()
                hideRecyclerView()
                page = 1
            }

            override fun onViewAttachedToWindow(arg0: View) {
                // search was opened
            }
        })



        searchView.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {

                return false
            }
        })


    }

    private fun showAlertEmptyResult() {
        val alertDialog = AlertDialog.Builder(this@MainActivity).create()
        alertDialog.setTitle(getString(R.string.main_act_alert_empty_result_title))
        alertDialog.setMessage(getString(R.string.main_act_alert_empty_result_message))
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.main_act_alert_empty_result_ok)
        ) { dialog, which -> dialog.dismiss() }
        alertDialog.show()
    }

    private fun showAlertDetailEmptyResult() {
        val alertDialog = AlertDialog.Builder(this@MainActivity).create()
        alertDialog.setTitle(getString(R.string.main_act_alert_empty_result_title))
        alertDialog.setMessage(getString(R.string.main_act_empry_detail_result_message))
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.main_act_alert_empty_result_ok)
        ) { dialog, which -> dialog.dismiss() }
        alertDialog.show()
    }

    private fun showAlertSnackbarConnection() {
        val snackbar = Snackbar
                .make(coordinatorLayout, getString(R.string.main_act_alert_snack_bar_connection_text), Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.main_act_alert_snack_bar_connection_action)) {

                }
        snackbar.show()
    }

    private fun loadViews() {
        recyclerView = movie_recycler
        loadingDots = loading_dots
        textviewWellcome = textView_wellcome
        coordinatorLayout = coordinator_layout
    }


    fun clearRecyclerView() {
        movies?.clear()
        recyclerView.adapter?.notifyDataSetChanged()
    }


    fun showProgressDots() {
        loadingDots?.visibility = VISIBLE;
    }

    fun hideProgressDots() {
        loadingDots?.visibility = GONE;
    }


    fun showTextViewWellcome() {
        textviewWellcome.visibility = VISIBLE;
    }

    fun hideTextViewWellcome() {
        textviewWellcome.visibility = GONE;
    }

    fun showRecyclerView() {
        recyclerView.visibility = VISIBLE;
    }

    fun hideRecyclerView() {
        recyclerView.visibility = GONE;
    }


    private fun checkConnection(context: Context): Boolean {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }


}
