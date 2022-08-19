package com.example.italikachallenges.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.italikachallenges.R
import com.example.italikachallenges.databinding.FragmentGenericErrorDialogBinding


class GenericErrorDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val binding: FragmentGenericErrorDialogBinding =
            FragmentGenericErrorDialogBinding.inflate(layoutInflater, null, false)

        arguments?.let {
            binding.textErrorMessage.text = it.getString(MESSAGE_KEY)
        }
        return  AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .setPositiveButton("ok") { _, _ -> dismiss() }
            .create()

    }

    companion object {
        const val TAG = "GenericErrorDialog"
        private const val MESSAGE_KEY = "message_key"
        fun createBundle(errorMessage:String):Bundle{
            val bundle = Bundle()
            bundle.putString(MESSAGE_KEY,errorMessage)
            return bundle
        }
    }
}