package com.mk8.screen.extension

import android.os.Build.VERSION.SDK_INT
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoaderBuilder
import coil.api.load
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.mk8.screen.base.BaseRecyclerView

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun ViewGroup.inflateLayout(@LayoutRes layoutRes: Int): View = LayoutInflater.from(context).inflate(layoutRes, this, false)

fun ImageView.loadFromUrlCircle(image: String) {
    this.load(image) {
        transformations(CircleCropTransformation())
        target {
            setImageDrawable(it)
        }
    }
}

fun ImageView.loadFromUrlRoundedCorners(image: String) {
    this.load(image) {
        transformations(RoundedCornersTransformation(1f))
        target {
            setImageDrawable(it)
        }
    }
}

fun ImageView.loadGif(image: String) {
    val imageLoader = ImageLoaderBuilder(context).componentRegistry {
        if (SDK_INT >= 28) {
            add(ImageDecoderDecoder())
        } else {
            add(GifDecoder())
        }
    }.build()

    load(image, imageLoader) {
        target {
            setImageDrawable(it)
        }
    }
}

fun RecyclerView.initRecycler(adapter: BaseRecyclerView<*, *>) = run {
    layoutManager = LinearLayoutManager(context)
    setHasFixedSize(true)
    this.adapter = adapter
}