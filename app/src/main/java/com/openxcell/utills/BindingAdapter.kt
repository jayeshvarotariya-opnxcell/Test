package com.openxcell.utills

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.openxcell.R


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
        val imm = view.context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

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
