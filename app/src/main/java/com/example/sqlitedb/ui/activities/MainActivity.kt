package com.example.sqlitedb.ui.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqlitedb.R
import com.example.sqlitedb.adapter.PersonAdapter
import com.example.sqlitedb.base.BaseActivity
import com.example.sqlitedb.databinding.ActivityMainBinding
import com.example.sqlitedb.db.DatabaseHandler
import com.example.sqlitedb.model.PersonData
import com.example.sqlitedb.utilities.Utility
import com.google.android.material.textfield.TextInputEditText

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseHandler
    lateinit var mPersonAdapter : PersonAdapter
    var mPersonData = ArrayList<PersonData>()
    private val databaseHandler = DatabaseHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getPersonDataFromDatabase = databaseHandler.getPerson()

        if (getPersonDataFromDatabase != null) {
            mPersonData.addAll(getPersonDataFromDatabase)
        }

        binding.recyclerSaveData.apply {
            layoutManager = LinearLayoutManager(context)
            mPersonAdapter = PersonAdapter(context, mPersonData)
            adapter = mPersonAdapter
            mPersonAdapter.notifyDataSetChanged()
        }

        //floating button to open add Person Details Alert Dialog
        binding.floatingButton.setOnClickListener {

            val dialogBuilder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.layout_add_details, null)
            dialogBuilder.setView(dialogView)
            dialogBuilder.show()

//            val setImage = dialogView.findViewById<ImageView>(R.id.image)
            val setName = dialogView.findViewById<TextInputEditText>(R.id.name)
            val setEmail = dialogView.findViewById<TextInputEditText>(R.id.email)
            val setPhone = dialogView.findViewById<TextInputEditText>(R.id.phone)
            val saveButton = dialogView.findViewById<Button>(R.id.btn_save)
            val cancelButton = dialogView.findViewById<Button>(R.id.btn_cancel)

//            dialogBuilder.setTitle("This is Title")
//            dialogBuilder.setMessage("THis is Msg")

            //save button in Alert Dialog to save Person Details
            saveButton.setOnClickListener {
//                val saveImage =

                val saveName = setName.text.toString()
                val saveEmail = setEmail.text.toString()
                val savePhone = setPhone.text.toString()

                val databaseHandler = DatabaseHandler(this)

                if (saveName.trim() != "" && saveEmail.trim() != "" && savePhone.trim() != "") {
                    val status = databaseHandler.addPerson(
                        PersonData(
                            saveName,
                            saveEmail,
                            savePhone
                        )
                    )
//                    mPersonData.addAll(listOf(PersonData(saveName,saveEmail,savePhone)))

                    if (status > -1) {
                        Utility().showToast(this, "Record saved Successfully")
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Utility().showToast(this, "Something went wrong")
                    }
                }
            }

            //cancel button in Alert Dialog to close it
            cancelButton.setOnClickListener {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}