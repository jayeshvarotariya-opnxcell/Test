package com.openxcell.utills

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.openxcell.R
import com.openxcell.data.pojo.DataEntity
import com.openxcell.data.pojo.UserRepo
import com.openxcell.ui.list.ListAdapter
import com.openxcell.ui.list.ListViewModel
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener


@BindingAdapter("snackBar")
fun setSnackBar(view: View, message: String?) {

    message?.let {
        val snackbar = Snackbar.make(view, "" + message, Snackbar.LENGTH_LONG)
        // Get the Snackbar's layout view
        val layout = snackbar.view as Snackbar.SnackbarLayout
        layout.setBackgroundResource(R.color.red)
        snackbar.show()
    }

}


@BindingAdapter("hideKeyBord")
fun hideKeyBord(view: View, showHide: Boolean?) {

    showHide?.let {
        val imm =
            view.context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

        val activity = view.context as AppCompatActivity
        //Find the currently focused view, so we can grab the correct window token from it.
        var currantView = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (currantView == null) {
            currantView = View(activity)
        }
        imm.hideSoftInputFromWindow(currantView.windowToken, 0)
    }

}


@BindingAdapter("app:errorText")
fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
    view.error = errorMessage
}


@BindingAdapter("app:initSwipe")
fun setSwipeLayout(view: SmartRefreshLayout, viewModel: ListViewModel) {

    view.setOnRefreshListener(object : OnRefreshListener {
        override fun onRefresh(refreshLayout: RefreshLayout) {
            viewModel.page = 1
            viewModel.retrieveDataList()
        }
    })

    view.setOnLoadMoreListener(object : OnLoadMoreListener {

        override fun onLoadMore(refreshLayout: RefreshLayout) {
            viewModel.retrieveDataList()
        }
    })

}

@BindingAdapter("app:onSwipeError")
fun setSwipeLayout(view: SmartRefreshLayout, e: Throwable?) {

    view.finishLoadMore(false)
    view.finishRefresh(false)

}

@BindingAdapter("app:onSwipeSuccess")
fun setSwipeLayout(view: SmartRefreshLayout, data: UserRepo?) {

    view.finishRefresh()
    view.finishLoadMore()
    if(data!=null&&data.data?.size==0){
        view.finishLoadMoreWithNoMoreData()
        view.setEnableLoadMore(false)
    }
}

@BindingAdapter("app:onListLoaded")
fun setSwipeLayout(view: RecyclerView, data: UserRepo?) {
    data?.data?.let {
        if (view.adapter is ListAdapter && data.page > 1) {
            (view.adapter as ListAdapter).mData.addAll(it)
            (view.adapter as ListAdapter).notifyDataSetChanged()
        } else {
            val mDataList : MutableList<DataEntity> = ArrayList()
            mDataList.addAll(it)
            view.adapter = ListAdapter(mDataList)
        }
    }

}


@BindingAdapter("app:glideImageRound")
fun setImageRound(view: AppCompatImageView, path: String) {

    Glide.with(view)
        .load(path)
        .apply(RequestOptions.circleCropTransform())
        .into(view)


}


@BindingAdapter("app:glideImage")
fun setImage(view: AppCompatImageView, path: String) {

    Glide.with(view)
        .load(path)
        .into(view)


}