import com.sxit.campus_link.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("/api/users")
    fun getUsers(): Call<List<User>>

    @POST("/api/users/register")
    fun register(@Body user: User): Call<User>

    @POST("/api/users/login")
    fun login(@Body user: User): Call<User>
}