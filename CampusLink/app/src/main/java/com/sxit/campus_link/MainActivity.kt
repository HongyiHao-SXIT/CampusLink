package com.sxit.campus_link
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sxit.campus_link.databinding.ActivityMainBinding
import com.sxit.campus_link.network.ApiClient
import com.sxit.campus_link.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonFetch.setOnClickListener {
            ApiClient.instance.getUsers().enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    val users = response.body() ?: emptyList()
                    binding.textViewResult.text = users.joinToString("\n") { it.username }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    binding.textViewResult.text = "错误: ${t.message}"
                }
            })
        }
    }
}
