package com.example.sqlitedb.viewmodel

import android.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sqlitedb.R
import com.example.sqlitedb.databinding.LayoutAddDetailsBinding
import com.example.sqlitedb.db.DatabaseHandler
import com.example.sqlitedb.model.PersonData
import com.example.sqlitedb.ui.activities.MainActivity
import com.example.sqlitedb.utilities.Utility

class MainViewModel() : ViewModel() {
    val mutableLiveData = MutableLiveData<ArrayList<PersonData>>()
    private lateinit var binding: LayoutAddDetailsBinding

    //floating button function to open add Person Details Alert Dialog
    fun floatingButtonClick() {

        val dialogBuilder = AlertDialog.Builder(MainActivity())
        val dialogView = MainActivity().layoutInflater.inflate(R.layout.layout_add_details, null)
        dialogBuilder.setView(dialogView)
        dialogBuilder.show()

        //save button function to save Person Details
        fun saveButtonClick() {
            val saveName = binding.name.text.toString()
            val saveEmail = binding.email.text.toString()
            val savePhone = binding.phone.text.toString()

            val databaseHandler = DatabaseHandler(MainActivity())

            if (saveName.trim() != "" && saveEmail.trim() != "" && savePhone.trim() != "") {
                val status = databaseHandler.addPerson(
                    PersonData(
                        saveName,
                        saveEmail,
                        savePhone
                    )
                )
                if (status > -1) {
                    Utility().showToast(MainActivity(), "Record Saved Successfully")

                } else {
                    Utility().showToast(MainActivity(), "Something went wrong")
                }
            }
        }

        //cancel button function to close Alert Dialog
        fun cancelButtonClick() {

        }

        //update particular Person
        fun updateButtonClick() {

        }

        //delete particular Person
        fun deleteButtonClick() {

        }

        fun deleteAllButtonClick() {

        }



    }
}