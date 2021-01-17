package com.project.pokedex.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChanges
import com.project.pokedex.R
import com.project.pokedex.network.models.PokemonInfo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.concurrent.TimeUnit

class LoginFragment : Fragment() {
    private val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameChanged = trainerName.textChanges()
            .skipInitialValue()
            .debounce(400, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())

        disposables.add(
            nameChanged
                .subscribe {
                    trainerName.error =
                        if (TextUtils.isEmpty(it)) "Required field." else null
                }
        )

        disposables.add(
            trainerName.textChanges()
                .subscribe{
                    val areValidFields: Boolean = !TextUtils.isEmpty(trainerName.text.toString())
                    submitButton.isEnabled = areValidFields
                }
        )
        disposables.add(
            submitButton.clicks()
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe {
                    val action = LoginFragmentDirections.actionLoginFragmentToPokemonViewFragment(trainerName.text.toString())
                    findNavController().navigate(action)
                }
        )



        }

    override fun onDestroyView() {
        disposables.clear()
        super.onDestroyView()
    }

    }





