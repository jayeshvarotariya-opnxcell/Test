package com.openxcell.utills

import android.view.View
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


@BindingAdapter("app:errorText")
fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
        view.error = errorMessage
}
