package com.jetpack.mvvm_rooom.view

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetpack.mvvm_rooom.R
import com.jetpack.mvvm_rooom.viewmodel.MainActivityViewModel
import com.jetpack.mvvm_rooom.databinding.ActivityMainBinding
import com.jetpack.mvvm_rooom.other.AppUtils.Companion.loadPic
import com.jetpack.mvvm_rooom.repositories.room.PersonTable
import com.jetpack.mvvm_rooom.view.adapters.PersonAdapter
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    //Initialized the ViewModel from koin(lazy) dependency injection.
    private val viewModel by viewModel<MainActivityViewModel>()

    //data binding to avoid the FindViewById .
    private lateinit var dataBinding: ActivityMainBinding

    //Persons Adapter
    private val personAdapter: PersonAdapter by inject()
    private lateinit var personImgBitmap: Bitmap

    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //initialize the Views
        initViews()
        //Initialize ViewModel observer
        initViewModelObserver()

        initActivityResult()
    }

    private fun initActivityResult() {
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result?.resultCode == RESULT_OK && result.data != null) {
                    val bitmap: Bitmap
                    val bundle = result.data!!.extras
                    bitmap = if (bundle != null) {
                        bundle.get("data") as Bitmap
                    } else {
                        val uri = result.data!!.data
                        MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
                    }
                    if (bitmap != null) {
                        personImgBitmap = bitmap
                        loadPic(dataBinding.personImg, bitmap)
                    }
                }
            }
    }

    private fun initViews() {
        dataBinding.apply {
            //initialise the recyclerview
            personsRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            personsRecyclerView.setHasFixedSize(true)
            personsRecyclerView.adapter = personAdapter
            btnSubmit.setOnClickListener {
                viewModel.insertPersonData(
                    PersonTable(
                        personName = etPersonName.text.toString(),
                        personNo = etPersonMobileNo.text.toString(),
                        personImg = personImgBitmap
                    )
                )
            }
            personImg.setOnClickListener { choosePhoto() }
        }
    }

    private fun choosePhoto() {
        checkImagePermission()
    }

    private fun checkImagePermission() {
        Dexter.withContext(this).withPermissions(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(permissions: MultiplePermissionsReport?) {
                if (permissions!!.areAllPermissionsGranted())
                    selectFile()
                if (permissions.isAnyPermissionPermanentlyDenied)
                    Toast.makeText(
                        this@MainActivity,
                        "Camera access needed to take pictures!!",
                        Toast.LENGTH_SHORT
                    ).show()
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<PermissionRequest>?,
                p1: PermissionToken?
            ) {

            }

        }).withErrorListener {
            Toast.makeText(
                this,
                "Error!!!!!",
                Toast.LENGTH_SHORT
            ).show()
        }
            .onSameThread()
            .check()

    }

    private fun selectFile() {
        val items = arrayOf<CharSequence>(
            getString(R.string.choose_from_camera),
            getString(R.string.choose_from_gallery)
        )
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.choose_source)
        builder.setItems(items) { _, item ->
            if (items[item] == getString(R.string.choose_from_camera)) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (intent.resolveActivity(packageManager) != null) {
                    activityResultLauncher.launch(intent)
                }

            } else if (items[item] == getString(R.string.choose_from_gallery)) {
                val intent = Intent()
                intent.type = "image/*"
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.action = Intent.ACTION_GET_CONTENT
                activityResultLauncher.launch(intent)
            }
        }
        builder.show()
    }

    private fun initViewModelObserver() {
        viewModel.personDataList.observe(this, {
            it?.let {
                personAdapter.setPersonsData(it)
            }
        })
    }


}