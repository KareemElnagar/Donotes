package com.kareem.donotes

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.kareem.donotes.database.NotesDatabase
import com.kareem.donotes.databinding.FragmentCreateNoteBinding
import com.kareem.donotes.entities.Notes
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class CreateNoteFragment : BaseFragment<FragmentCreateNoteBinding>(),
    EasyPermissions.PermissionCallbacks, EasyPermissions.RationaleCallbacks {
    private var currentDate: String? = null
    override val LOG_TAG: String = CreateNoteFragment::class.java.simpleName
    override val LayoutInflater: (LayoutInflater) -> FragmentCreateNoteBinding =
        FragmentCreateNoteBinding::inflate

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setup() {

        notesId = requireArguments().getInt("notesId", -1)

        if (notesId != -1) {
            lifecycleScope.launch {

                context?.let {
                    val notes = NotesDatabase.getDaoInstance(
                        NotesApplication.getApplicationContext()
                    ).getSpecifiedNote(notesId)

                    binding.apply {
                        noteTitle.setText(notes.title)
//                        if (notes.imgPath != "") {
//                            SELECTED_IMG_PATH = notes.imgPath!!
//                            imageNote.setImageBitmap(BitmapFactory.decodeFile(notes.imgPath))
//                            imageNote.visibility = View.VISIBLE
//                        } else {
//                            imageNote.visibility = View.GONE
//
//                        }
                        noteText.setText(notes.noteText)
                    }


                }
            }
        }


        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val currentDate = LocalDateTime.now().format(formatter)

        binding.apply {
            buttonDone.setOnClickListener {
                saveNote()
            }
            buttonBack.setOnClickListener {
                replaceFragment(HomeFragment(), true)
            }
            TVdateTime.text = currentDate
            AddImage.setOnClickListener {
                readStorageTask()
            }
        }

    }

    private fun saveNote() {
        binding.apply {

            if (noteTitle.text.isNullOrEmpty()) {
                Toast.makeText(context, "Note Title is Required", Toast.LENGTH_SHORT).show()

            } else if (noteText.text.isNullOrEmpty()) {

                Toast.makeText(context, "Note Description is Required", Toast.LENGTH_SHORT).show()
            } else {

                lifecycleScope.launch {
                    val notes = Notes()
                    notes.title = noteTitle.text.toString()
                    notes.noteText = noteText.text.toString()
                    notes.dateTime = currentDate
                    notes.imgPath = SELECTED_IMG_PATH
                    context?.let {
                        NotesDatabase.getDaoInstance(it).insertNotes(notes)
                        noteTitle.setText("")
                        imageNote.visibility = View.GONE
                        noteText.setText("")
                        requireActivity().supportFragmentManager.popBackStack()
                    }
                }
            }
        }


    }

    private fun hasReadStoragePerm(): Boolean {
        return EasyPermissions.hasPermissions(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

    }

    private fun hasWriteStoragePerm(): Boolean {
        return EasyPermissions.hasPermissions(
            requireContext(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

    }

    private fun readStorageTask() {
        if (hasReadStoragePerm()) {
            pickImgFromGallery()

        } else {
            EasyPermissions.requestPermissions(
                requireActivity(),
                "This app needs access to your storage",
                READ_STORAGE_PERM,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    }

    private fun pickImgFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivityForResult(intent, REQUEST_CODE_IMAGE)
        }
    }

    @Deprecated(
        "Deprecated in Java",
        ReplaceWith("super.onActivityResult(requestCode, resultCode, data)")
    )
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK) {
            if (data != null) {
                val selectedImgURL = data.data
                if (selectedImgURL != null) {
                    try {
                        val inputStream =
                            requireActivity().contentResolver.openInputStream(selectedImgURL)
                        val bitMap = BitmapFactory.decodeStream(inputStream)
                        binding.apply {
                            imageNote.setImageBitmap(bitMap)
                            imageNote.visibility = View.VISIBLE
                        }
                        SELECTED_IMG_PATH = getPathFromURI(selectedImgURL)!!


                    } catch (e: Exception) {
                        Toast.makeText(requireContext(), e.message.toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    @SuppressLint("Recycle")
    private fun getPathFromURI(contentUri: Uri): String? {
        val filePath: String?
        val cursor = requireActivity().contentResolver.query(
            contentUri,
            null,
            null,
            null,
            null,
            null
        )
        if (cursor == null) {
            filePath = contentUri.path
        } else {
            cursor.moveToFirst()
            var index = cursor.getColumnIndex("_data")
            filePath = cursor.getString(index)
            cursor.close()
        }
        return filePath
    }


    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        TODO("Not yet implemented")
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(requireActivity(), perms)) {
            AppSettingsDialog.Builder(requireActivity()).build().show()

        }
    }

    override fun onRationaleAccepted(requestCode: Int) {
        TODO("Not yet implemented")
    }

    override fun onRationaleDenied(requestCode: Int) {
        TODO("Not yet implemented")
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CreateNoteFragment().apply {
                arguments = Bundle().apply {

                }
            }

        var READ_STORAGE_PERM = 123
        var WRITE_STORAGE_PERM = 123
        var REQUEST_CODE_IMAGE = 111
        var SELECTED_IMG_PATH = ""
        var isEdit = ""
        var notesId = -1

    }


}