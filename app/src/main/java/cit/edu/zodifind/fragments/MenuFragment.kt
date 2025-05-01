package cit.edu.zodifind.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cit.edu.zodifind.DeveloperActivity
import cit.edu.zodifind.HomeActivity
import cit.edu.zodifind.LoginActivity
import cit.edu.zodifind.ProfileActivity
import cit.edu.zodifind.R
import cit.edu.zodifind.SettingsActivity
import cit.edu.zodifind.app.ZodiFindApplication

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val app = requireActivity().application as ZodiFindApplication

        view.findViewById<View>(R.id.menuProfile)?.setOnClickListener {
            val intent = Intent(requireContext(), ProfileActivity::class.java)
            startActivity(intent)
            true
        }
        view.findViewById<View>(R.id.menuDevelopers)?.setOnClickListener {
            val intent = Intent(requireContext(), DeveloperActivity::class.java)
            startActivity(intent)
            true
        }
        view.findViewById<View>(R.id.menuSettings)?.setOnClickListener {
            val intent = Intent(requireContext(), SettingsActivity::class.java)
            startActivity(intent)
            true
        }
        view.findViewById<View>(R.id.menuLogout)?.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            app.logout()
            startActivity(intent)
        }
    }

    companion object {
        fun newInstance(): MenuFragment {
            return MenuFragment()
        }
    }
}