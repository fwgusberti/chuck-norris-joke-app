package br.com.nerdrapido.chucknorrisjokeapp.helper

import android.widget.ImageView
import br.com.nerdrapido.chucknorrisjokeapp.R
import com.bumptech.glide.Glide

/**
 * Created By FELIPE GUSBERTI @ 06/01/2021
 */
object ViewHelper {

    fun loadImageUrlIntoView(imageUrl: String, imageView: ImageView) {
        Glide.with(imageView.context.applicationContext)
            .load(imageUrl)
            .placeholder(R.drawable.img_progress)
            .error(R.drawable.ic_broken_image)
            .into(imageView)
    }
}
