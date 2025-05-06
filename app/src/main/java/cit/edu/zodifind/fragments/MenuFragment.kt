package cit.edu.zodifind.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cit.edu.zodifind.activities.DeveloperActivity
import cit.edu.zodifind.activities.LoginActivity
import cit.edu.zodifind.activities.ProfileActivity
import cit.edu.zodifind.R
import cit.edu.zodifind.activities.SettingsActivity
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
        val user = app.currentUser ?: return

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
            val intent = Intent(requireContext(), SettingsActivity::class.java).apply {
                putExtra("name", user.name)
                putExtra("bio", user.bio)
                putExtra("username", user.username)
                putExtra("bday", user.birthdate?.toString())
                putExtra("profileImageUri", user.profileImageUri)
            }
            startActivity(intent)
            true
        }
        view.findViewById<View>(R.id.menuLogout)?.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes") { dialog, _ ->

                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    app.logout()
                    startActivity(intent)
                    dialog.dismiss()
                }
                .setNegativeButton("No") { dialog, _ ->

                    dialog.dismiss()
                }
                .show()
        }
    }

    companion object {
        fun newInstance(): MenuFragment {
            return MenuFragment()
        }
    }
}