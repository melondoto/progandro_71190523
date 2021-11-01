package com.example.pertemuan8_71190523

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Toolbar
        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //Pager
        val viewPager = findViewById<ViewPager2>(R.id.pager)
        val listFragment: ArrayList<Fragment> = arrayListOf(FirstFragment(), SecondFragment(), ThirdFragment())
        val pagerAdapter = PagerAdapter(this, listFragment)
        viewPager.adapter = pagerAdapter
    }

    class PagerAdapter(val activity: AppCompatActivity, val listFragment: ArrayList<Fragment>): FragmentStateAdapter(activity) {
        override fun getItemCount(): Int {
            return listFragment.size
        }
        override fun createFragment(position: Int): Fragment {
            return listFragment.get(position)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
//            R.id.page1 -> Toast.makeText(this,"Halaman 1", Toast.LENGTH_SHORT).show()
//            R.id.page2 -> startActivity(Intent(this, SecondActivity::class.java))
//            R.id.page3 -> startActivity(Intent(this, ThirdActivity::class.java))
            R.id.page1 -> {
                val viewPager = findViewById<ViewPager2>(R.id.pager)
                viewPager.setCurrentItem(0)
                true
            }
            R.id.page2 -> {
                val viewPager = findViewById<ViewPager2>(R.id.pager)
                viewPager.setCurrentItem(1)
                true
            }
            R.id.page3 -> {
                val viewPager = findViewById<ViewPager2>(R.id.pager)
                viewPager.setCurrentItem(2)
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}