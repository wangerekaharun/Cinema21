package com.gibsoncodes.movieapp.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

 abstract class BaseDataBindingActivity: AppCompatActivity() {
     protected inline fun <reified T : ViewDataBinding> binding(
         @LayoutRes resId: Int
     ): Lazy<T> = lazy { DataBindingUtil.setContentView<T>(this, resId) }
 }
