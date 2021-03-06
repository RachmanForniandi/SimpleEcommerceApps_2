package com.example.simpleecommerceapp.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.simpleecommerceapp.R
import com.example.simpleecommerceapp.ui.login.LoginActivity
import com.example.simpleecommerceapp.utils.SessionManager
import kotlinx.android.synthetic.main.fragment_profile.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.newTask
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.startActivity


class ProfileFragment : Fragment() {

    private var session: SessionManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        session = SessionManager(activity!!)
        tvNama.text = session?.fullname
        tvNohp.text = session?.nohp
        tvAlamat.text = session?.alamat


        btnLogout.onClick {
            session?.logout()
            startActivity(intentFor<LoginActivity>().clearTop().newTask().clearTask())
        }
    }




}
