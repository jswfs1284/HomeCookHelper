package com.example.homecookhelper.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homecookhelper.R
import com.example.homecookhelper.database.DatabaseModule
import kotlinx.android.synthetic.main.fragment_recipe_list.*
import kotlinx.android.synthetic.main.fragment_recipe_list.view.*
import kotlinx.android.synthetic.main.fragment_recipe_list.view.fab_add_note

/**
 * A simple [Fragment] subclass.
 */
class RecipeListFragment : Fragment() {

    /* noteDao를 Lazy 키워드를 이용하여 처음 호출될때 초기화하도록 설정 */
    val recipeDao by lazy { DatabaseModule.getDatabase(requireContext()).recipeDao() }

    //어댑터 생성
    val recipeAdapter = RecipeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //fragment_list 인플레이션
        val rootView = inflater.inflate(R.layout.fragment_recipe_list, container, false)

        /* 어댑터 초기화*/
        rootView.list_recipe.adapter = recipeAdapter
        rootView.list_recipe.layoutManager = LinearLayoutManager(requireContext())

        rootView.fab_add_note.setOnClickListener {
            findNavController().navigate(R.id.action_recipeListFragment_to_writeRecipe)
        }

        return rootView// 생성한 fragment_list 뷰 반환
    }//end of onCreateView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeDao.selectRecipe().observe(viewLifecycleOwner, androidx.lifecycle.Observer{
            recipeAdapter.recipes = it//어댑터에 변경된 note 전달
            recipeAdapter.notifyDataSetChanged()//어댑터에 변경 공지
        })
    }//end of onViewCreated
    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
