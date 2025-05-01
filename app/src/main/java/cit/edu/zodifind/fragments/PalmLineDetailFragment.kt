package cit.edu.zodifind.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import cit.edu.zodifind.R

class PalmLineDetailFragment : Fragment() {

    private var imageResId: Int = 0
    private var titleResId: Int = 0
    private var domainResId: Int = 0
    private var contentResId: Int = 0

    private lateinit var backButton: ImageView
    private lateinit var palmLineImage: ImageView
    private lateinit var tvPalmTitle: TextView
    private lateinit var tvPalmDomain: TextView
    private lateinit var palmLineContent: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageResId = it.getInt("image_res_id")
            titleResId = it.getInt("title_res_id")
            domainResId = it.getInt("domain_res_id")
            contentResId = it.getInt("content_res_id")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_palmistry_focused_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backButton = view.findViewById(R.id.backButton)
        palmLineImage = view.findViewById(R.id.palmLineImage)
        tvPalmTitle = view.findViewById(R.id.tvPalmTitle)
        tvPalmDomain = view.findViewById(R.id.tvPalmDomain)
        palmLineContent = view.findViewById(R.id.palmLineContent)

        palmLineImage.setImageResource(imageResId)
        tvPalmTitle.text = context?.getString(titleResId)
        tvPalmDomain.text = context?.getString(domainResId)
        palmLineContent.text = context?.getString(contentResId)

        backButton.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    companion object {
        fun newInstance(imageRes: Int, titleRes: Int, domainRes: Int, contentRes: Int): PalmLineDetailFragment {
            return PalmLineDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt("image_res_id", imageRes)
                    putInt("title_res_id", titleRes)
                    putInt("domain_res_id", domainRes)
                    putInt("content_res_id", contentRes)
                }
            }
        }
    }
}