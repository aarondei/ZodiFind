package cit.edu.zodifind.fragments

import android.animation.ObjectAnimator
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import cit.edu.zodifind.R
import cit.edu.zodifind.data.TarotCards
import cit.edu.zodifind.databinding.FragmentLuckBinding
import cit.edu.zodifind.luck.LuckyModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint

class LuckFragment : Fragment() {


    private var _binding: FragmentLuckBinding? = null
    private val binding get() = _binding!!

    private lateinit var mediaPlayer: MediaPlayer

    private var currentStep = 0

    private lateinit var pastCard: LuckyModel
    private lateinit var presentCard: LuckyModel
    private lateinit var futureCard: LuckyModel

    @Inject
    lateinit var tarotCards: TarotCards

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        preparePrediction()
        initListeners()
    }

    private fun preparePrediction() {
        val drawnCards = tarotCards.getLuckyList().shuffled().take(3)
        pastCard = drawnCards[0]
        presentCard = drawnCards[1]
        futureCard = drawnCards[2]

        // Setup the screen in "waiting" state
        binding.tvLucky.text = "Tap to reveal your past, present, and future ✨" // You can replace this with a string resource like "Tap to reveal your past, present, and future ✨"
        binding.ivLuckyCard.setImageResource(R.drawable.card) // Default back image for the card
        binding.prediction.isVisible = false // Hide prediction at first
        binding.preview.isVisible = true // Show the preview screen

        // Optionally, you can show the past card's meaning initially
        binding.tvLucky.text = getString(pastCard.title) // Display title
        binding.tvLuckyDescription.text = getString(pastCard.pastMeaning) // Display past meaning
    }

    private fun shareResult(prediction:String) {
        val sendIntent:Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, prediction)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(sendIntent)
    }

    private fun initListeners() {
        binding.ivRoulette.setOnClickListener { spinRoulette() }
    }

    private fun spinRoulette() {

        mediaPlayer = MediaPlayer.create(context, R.raw.roulette)
        val random = java.util.Random()
        val degrees = random.nextInt(1440) + 360

        val animator = ObjectAnimator.ofFloat(binding.ivRoulette, View.ROTATION, 0f, degrees.toFloat())
        mediaPlayer.start()
        binding.card.isVisible = false
        animator.duration = 1500
        animator.interpolator = DecelerateInterpolator()
        animator.doOnEnd { slideCard() }
        animator.start()
    }

    private fun slideCard() {
        val slideUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)

        mediaPlayer = MediaPlayer.create(context, R.raw.card_slide)

        slideUpAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {

                mediaPlayer.start()
                binding.card.isVisible = true
            }
            override fun onAnimationEnd(animation: Animation?) {
                growCard()
            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })
        binding.card.startAnimation(slideUpAnimation)
    }

    private fun growCard(){
        val growAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.grow)

        growAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                binding.card.isVisible= false
                showPrediction()
            }

            override fun onAnimationRepeat(animation: Animation?) {}

        })

        binding.card.startAnimation(growAnimation)
    }

    private fun showPrediction(){

        mediaPlayer = MediaPlayer.create(context, R.raw.showing_card)

        val disappearAnimation = AlphaAnimation(1.0f, 0.0f)
        disappearAnimation.duration = 200

        val appearAnimation = AlphaAnimation(0.0f, 1.0f)
        appearAnimation.duration = 1000

        disappearAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                binding.preview.isVisible = false
                binding.prediction.isVisible = true

                // 👉 Start showing first card
                currentStep = 0
                showCurrentStep()
            }

            override fun onAnimationRepeat(animation: Animation?) {}


//        mediaPlayer = MediaPlayer.create(context, R.raw.showing_card)
//
//        val disappearAnimation = AlphaAnimation(1.0f, 0.0f)
//        disappearAnimation.duration = 200
//
//        val appearAnimation = AlphaAnimation(0.0f, 1.0f)
//        appearAnimation.duration = 1000
//
//        disappearAnimation.setAnimationListener(object : Animation.AnimationListener {
//            override fun onAnimationStart(animation: Animation?) {}
//            override fun onAnimationEnd(animation: Animation?) {
//                mediaPlayer.start()
//                binding.preview.isVisible = false
//                binding.prediction.isVisible = true
//            }
//            override fun onAnimationRepeat(animation: Animation?) {}
//
        })
        binding.preview.startAnimation(disappearAnimation)
        binding.prediction.startAnimation(appearAnimation)

        // Set click to next card
        binding.prediction.setOnClickListener {
            nextStep()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLuckBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun showCurrentStep() {

        mediaPlayer.start();

        when (currentStep) {
            0 -> {
                // Past card
                binding.tvCardHeader.text = "Past" // Set the header to "Past"
                binding.tvLucky.text = getString(pastCard.title) // Set title for past card
                binding.tvLuckyDescription.text = getString(pastCard.pastMeaning) // Set meaning for past
                binding.ivLuckyCard.setImageResource(pastCard.image) // Set image for past card
            }
            1 -> {
                // Present card
                binding.tvCardHeader.text = "Present" // Set the header to "Present"
                binding.tvLucky.text = getString(presentCard.title) // Set title for present card
                binding.tvLuckyDescription.text = getString(presentCard.presentMeaning) // Set meaning for present
                binding.ivLuckyCard.setImageResource(presentCard.image) // Set image for present card
            }
            2 -> {
                // Future card
                binding.tvCardHeader.text = "Future" // Set the header to "Future"
                binding.tvLucky.text = getString(futureCard.title) // Set title for future card
                binding.tvLuckyDescription.text = getString(futureCard.futureMeaning) // Set meaning for future
                binding.ivLuckyCard.setImageResource(futureCard.image) // Set image for future card
            }
        }
    }


    private fun nextStep() {
        if (currentStep < 2) {
            currentStep++
            showCurrentStep() // Show the next card and update the header
        } else {
            // Optional: maybe show a "Done" or disable clicking after the last card
        }
    }


}

