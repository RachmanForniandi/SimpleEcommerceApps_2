package com.example.simpleecommerceapp.ui.register

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.simpleecommerceapp.R
import com.example.simpleecommerceapp.models.Register.ResponseRegister
import com.example.simpleecommerceapp.utils.hide
import com.example.simpleecommerceapp.utils.show
import kotlinx.android.synthetic.main.register_fragment.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.toast

class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.register_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        // TODO: Use the ViewModel
        attachObserver()

        btnRegister.onClick {
            viewModel.registerUser(
                "${etUsername.text}",
                "${etPassword.text}",
                "${etFullname.text}",
                "${etNoHp.text}",
                "${etAlamat.text}"
            )
        }
    }

    private fun attachObserver() {
        viewModel.responseRegister.observe(this, Observer { showResponse(it) })
        viewModel.errorApi.observe(this, Observer { showError(it) })
        viewModel.isLoadingProgressBar.observe(this, Observer { showLoading(it) })
        viewModel.isEmpty.observe(this, Observer { showIsEmpty(it) })
        viewModel.finish.observe(this, Observer { actionFinish(it) })
    }

    private fun actionFinish(it:Boolean?) = if (it ?: false)activity?.finish()else null

    private fun showIsEmpty(it:Boolean?) = if (it ?: true)toast("Register  Invalid")else null

    private fun showLoading(it:Boolean?) = if (it ?: false) pbRegister.show()else pbRegister.hide()

    private fun showError(it: Throwable?) = toast(it?.message ?: "")

    private fun showResponse(it: ResponseRegister?)= toast(it?.message ?: "")



}
