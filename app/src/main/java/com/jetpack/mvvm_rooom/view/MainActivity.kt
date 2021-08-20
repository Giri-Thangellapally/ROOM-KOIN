package com.jetpack.mvvm_rooom.view

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.iceteck.silicompressorr.PathUtil
import com.iceteck.silicompressorr.SiliCompressor
import com.jetpack.mvvm_rooom.BuildConfig
import com.jetpack.mvvm_rooom.R
import com.jetpack.mvvm_rooom.viewmodel.MainActivityViewModel
import com.jetpack.mvvm_rooom.databinding.ActivityMainBinding
import com.jetpack.mvvm_rooom.repositories.room.PersonTable
import com.jetpack.mvvm_rooom.view.adapters.PersonAdapter
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class MainActivity : AppCompatActivity() {
    //Initialized the ViewModel from koin(lazy) dependency injection.
    private val viewModel by viewModel<MainActivityViewModel>()
    //data binding to avoid the FindViewById .
    private lateinit var dataBinding: ActivityMainBinding
    //Persons Adapter
    private val personAdapter: PersonAdapter by inject()
    private lateinit var destFile: File
    private val CAMERA_REQUEST:Int=0
    private val REQUEST_IMAGES_CODE:Int=1
    private val FOLDER = Environment.getExternalStorageDirectory().absolutePath
    private var strCompressImageFile = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //initialize the Views
        initViews()
        //Initialize ViewModel observer
        initViewModelObserver()
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
                        personImg = strCompressImageFile
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
        ).withListener(object :MultiplePermissionsListener{
            override fun onPermissionsChecked(permissions: MultiplePermissionsReport?) {
                if(permissions!!.areAllPermissionsGranted())
                    selectFile()
                if(permissions.isAnyPermissionPermanentlyDenied)
                    Toast.makeText(this@MainActivity,"Camera access needed to take pictures!!",Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<PermissionRequest>?,
                p1: PermissionToken?
            ) {

            }

        }).withErrorListener { Toast.makeText(
            this,
            "Error Occured!!!!!",
            Toast.LENGTH_SHORT
        ).show() }
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
        builder.setItems(items) { dialog, item ->
            if (items[item] ==getString(R.string.choose_from_camera)) {
                val time = System.currentTimeMillis().toString() + ".png"
                val builder = StrictMode.VmPolicy.Builder()
                StrictMode.setVmPolicy(builder.build())
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                destFile =
                    File(Environment.getExternalStorageDirectory(), time)
                intent.putExtra(
                    MediaStore.EXTRA_OUTPUT,
                    FileProvider.getUriForFile(
                        this,
                        BuildConfig.APPLICATION_ID+".provider",
                        destFile
                    )
                )
                startActivityForResult(intent, CAMERA_REQUEST)
            } else if (items[item] == getString(R.string.choose_from_gallery)) {
                val intent = Intent()
                intent.type = "image/*"
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(intent, REQUEST_IMAGES_CODE)
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                val dir = File(FOLDER + "/" + getString(R.string.app_name) + "/images/")
                if (!dir.exists()) {
                    dir.mkdirs()
                }
                strCompressImageFile =
                    SiliCompressor.with(this)
                        .compress(destFile.absolutePath, dir, true)
                dataBinding.personImg.load(File(strCompressImageFile))
            }
        }
        if (requestCode == REQUEST_IMAGES_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val dir = File(FOLDER + "/" + getString(R.string.app_name) + "/images/")
                if (!dir.exists()) {
                    dir.mkdirs()
                }
                val filePath = PathUtil.getPath(this, data?.data)
                strCompressImageFile = SiliCompressor.with(this)
                    .compress(filePath, dir, false)
                dataBinding.personImg.load(File(strCompressImageFile))

            }
        }

    }

}