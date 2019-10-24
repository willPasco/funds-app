package com.android.fundsapp.fundslist

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.fundsapp.AppApplication
import com.android.fundsapp.R
import com.android.fundsapp.data.entity.FundResponse
import com.android.fundsapp.domain.FundsListView
import com.android.fundsapp.domain.presenter.FundsListPresenter
import com.android.fundsapp.fundslist.adapter.FundsRecyclerAdapter
import kotlinx.android.synthetic.main.activity_funds_list.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject


class FundsListActivity : AppCompatActivity(), FundsListView {

    companion object {
        private const val REQUEST_CODE = 563
        private const val TAG = "FundsListActivity"
    }

    private lateinit var adapterListener: FundsRecyclerAdapter.OnItemClicked
    private lateinit var dialogMissConnection: Dialog
    private lateinit var dialogError: Dialog

    @Inject
    lateinit var presenter: FundsListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_funds_list)

        AppApplication.getMainComponent().inject(this)

        dialogError = buildErrorDialog()
        dialogMissConnection = buildNoConnectionDialog()
        setUpToolbar()

        adapterListener = object : FundsRecyclerAdapter.OnItemClicked {
            override fun onClick() {
                Toast.makeText(this@FundsListActivity, "Fake open detail", Toast.LENGTH_LONG).show()
            }
        }

        presenter.bind(this)

        doRequest()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            doRequest()
        } else {
            Log.e(TAG, "Unknown request code.")
        }
    }

    private fun doRequest() {
        if (isThereConnection())
            presenter.getFunds()
        else
            dialogMissConnection.show()
    }

    override fun showData(dataList: List<FundResponse>) {
        hideLoading()
        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler_view.adapter =
            FundsRecyclerAdapter(dataList, adapterListener)
    }

    override fun showError() {
        dialogError.show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
            text_toolbar_title.text = getString(R.string.app_name)
        supportActionBar?.setHomeButtonEnabled(false)
    }

    private fun buildNoConnectionDialog(): Dialog {

        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.error_no_connection)
        builder.setMessage(R.string.message_no_connection)
            .setPositiveButton(R.string.settings) { dialog, _ ->
                dialog.dismiss()
                startActivityForResult(Intent(Settings.ACTION_WIRELESS_SETTINGS), REQUEST_CODE)
            }
            .setNegativeButton(R.string.cancel) { _, _ ->
                finish()
            }

        return builder.create()
    }

    private fun buildErrorDialog(): Dialog {

        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.error)
        builder.setMessage(R.string.dialog_fire_missiles)
            .setPositiveButton(R.string.try_again) { dialog, _ ->
                dialog.dismiss()
                showLoading()
                presenter.getFunds()
            }
            .setNegativeButton(R.string.cancel) { _, _ ->
                finish()
            }

        return builder.create()
    }


    private fun isThereConnection(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo

        return activeNetwork != null && activeNetwork.isConnected
    }

    private fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        progress_bar.visibility = View.INVISIBLE
    }
}
