package com.example.myapplication

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.databinding.ActivityMain4Binding
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class MainActivity4 : AppCompatActivity() {

    private var imageCapture:ImageCapture?=null
    private lateinit var binding: ActivityMain4Binding
    private lateinit var outputDirectory: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        outputDirectory = getOutPutDirectory()

        if(allPermissionGranted()){
            startCamera()
        }else{
            ActivityCompat.requestPermissions(this,Constants.REQUIRED_PERMISSIONS,Constants.REQUEST_CODE_PERMISSIONS)
        }


        binding.BtnTakePhoto.setOnClickListener {
            takePhoto()
        }
    }

    private fun getOutPutDirectory(): File{
        val mediaDir = externalMediaDirs.firstOrNull()?.let{mFile ->
            File(mFile, resources.getString(R.string.app_name)).apply { mkdirs() }

        }
        return if(mediaDir !=null && mediaDir.exists())
            mediaDir else filesDir
    }


    private fun takePhoto(){
        val imageCapture = imageCapture?: return
        val photoFile = File(
            outputDirectory,SimpleDateFormat(Constants.FILE_NAME_FORMAT,
                Locale.getDefault()).format(System.currentTimeMillis()) +".jpg")
        val outputOption= ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(outputOption, ContextCompat.getMainExecutor(this),
            object :ImageCapture.OnImageSavedCallback{
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(photoFile)
                    val msg = "Photo Saved"
                    Toast.makeText(this@MainActivity4, "$msg $savedUri", Toast.LENGTH_LONG).show()
                }

                override fun onError(exception: ImageCaptureException) {
                   Log.e(Constants.TAG, "onError: ${exception.message}", exception)
                }

            }

        )
    }

    private fun startCamera(){
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview= Preview.Builder()
                .build()
                .also {
                    mPreview ->
                    mPreview.setSurfaceProvider(
                        binding.viewFinder.surfaceProvider
                    )
                }
            imageCapture = ImageCapture.Builder()
                .build()
            val CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this,CameraSelector,preview, imageCapture)
            }catch (e:Exception){
                Log.d(Constants.TAG,"Start Camera Failed",e)
            }
        }, ContextCompat.getMainExecutor(this))
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
          if(requestCode==Constants.REQUEST_CODE_PERMISSIONS){
              if(allPermissionGranted()){
                    startCamera()
              }else{
                  Toast.makeText(this, "Permission Not Granted", Toast.LENGTH_SHORT).show()

                  finish()
              }
          }
    }

    private fun allPermissionGranted() =
         Constants.REQUIRED_PERMISSIONS.all{
             ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
         }



}