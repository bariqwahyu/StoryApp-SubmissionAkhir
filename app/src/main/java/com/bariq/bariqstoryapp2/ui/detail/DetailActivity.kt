package com.bariq.bariqstoryapp2.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bariq.bariqstoryapp2.R
import com.bariq.bariqstoryapp2.data.response.ListStoryItem
import com.bariq.bariqstoryapp2.databinding.ActivityDetailBinding
import com.bariq.bariqstoryapp2.withDateFormat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.title = resources.getString(R.string.app_nameDetail)

        val story = intent.getParcelableExtra<ListStoryItem>(EXTRA_STORY)
        binding.apply {
            tvNameDetail.text = story?.name
            tvCreated.withDateFormat(story?.createdAt.toString())
            tvDesc.text = story?.description
        }
        Glide.with(this)
            .load(story?.photoUrl)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
            )
            .into(binding.imgDetail)

    }

    companion object {
        const val EXTRA_STORY = "extra_story"
    }
}