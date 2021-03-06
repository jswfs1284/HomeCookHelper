package com.example.homecookhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val controller = findNavController(R.id.navigation_host)

        NavigationUI.setupWithNavController(
            bottom_navigation, controller
        )

        controller.addOnDestinationChangedListener { _, destination, _ ->
            /* 최종 destination에서 네비바 없애기*/
            if (arrayListOf(
                    R.id.agricListFragment, R.id.agricSearchFragment, R.id.recipeListFragment,
                    R.id.infoFragment
                ).contains(destination.id)
            ) {
                bottom_navigation.visibility = View.VISIBLE
            } else {
                bottom_navigation.visibility = View.GONE
            }
        }

        // Top Level Destination 설정. (뒤로가기 안보이게 하기)
        NavigationUI.setupActionBarWithNavController(
            this,
            controller,
            AppBarConfiguration(
                setOf(
                    R.id.splashFragment,
                    R.id.loginFragment,
                    R.id.agricListFragment,
                    R.id.agricSearchFragment,
                    R.id.recipeListFragment,
                    R.id.infoFragment
                )
            )
        )
    }

    // 뒤로가기 버튼 눌렀을 때, 안꺼지고 뒤로가기
    override fun onSupportNavigateUp() = findNavController(R.id.navigation_host).navigateUp()
}
