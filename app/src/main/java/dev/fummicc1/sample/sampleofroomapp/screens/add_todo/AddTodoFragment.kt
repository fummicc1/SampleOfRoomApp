package dev.fummicc1.sample.sampleofroomapp.screens.add_todo

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dev.fummicc1.sample.sampleofroomapp.R
import dev.fummicc1.sample.sampleofroomapp.databinding.FragmentAddTodoBinding
import java.io.BufferedInputStream
import kotlin.math.log

class AddTodoFragment : Fragment() {

    lateinit var viewModel: AddTodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentAddTodoBinding>(
            inflater,
            R.layout.fragment_add_todo,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel = ViewModelProvider(this).get(AddTodoViewModel::class.java)
        binding.viewModel = viewModel
        binding.apply {
            taskEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    val task = p0.toString()
                    viewModel?.taskUpdated(task)
                }
            })
            viewModel?.let {
                it.imageSelected.observe(viewLifecycleOwner, { imageSelected ->
                    pickImageButton.visibility = if (imageSelected) View.GONE else View.VISIBLE
                    imageView.visibility = if (imageSelected) View.VISIBLE else View.GONE
                })
                it.todoImage.observe(viewLifecycleOwner, {
                    val uri = Uri.parse(it)
                    uri?.let { uri ->
                        context?.let { context ->
                            val inputStream = context.contentResolver.openInputStream(uri)
                            val bitmap = BitmapFactory.decodeStream(BufferedInputStream(inputStream))
                            imageView.setImageBitmap(bitmap)
                        }
                    }
                })
                it.completeSaving.observe(viewLifecycleOwner, {
                    findNavController().popBackStack()
                })
                it.successfulValidating.observe(viewLifecycleOwner, {
                    Snackbar.make(requireView(), getString(R.string.validate_error), Snackbar.LENGTH_SHORT).show()
                })
            }
            pickImageButton.setOnClickListener {
                openDocumentToPickImage()
            }
        }
        return binding.root
    }

    fun openDocumentToPickImage() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.setType("image/*")
        startActivityForResult(intent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            data?.data?.let {
                val uriString = it.toString()
                viewModel.imageSelected(uriString)
            }
        }
    }
}