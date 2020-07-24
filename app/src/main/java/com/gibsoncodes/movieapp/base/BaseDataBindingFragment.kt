package com.gibsoncodes.movieapp.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseDataBindingFragment:Fragment() {
  protected  inline fun <reified T:ViewDataBinding> binding(inflater:LayoutInflater,parent:ViewGroup?,
                                                            @LayoutRes resId:Int):T=
        DataBindingUtil.inflate(inflater,resId,parent,false)

}