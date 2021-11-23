package com.example.pagesixteen.ApiPackage;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
public interface LoginService {
    @Headers({
            "version:7",
            "orgurl:brigosha.classroom.digital"
    })
    @POST("/api/organisation/admin-login")
    Call<SignInResponse>  signInApiCall(@Body SignInRequest signInRequest);

  @Headers({"orgurl:brigosha.classroom.digital"})

    @GET("/api/master-care/admin?startDate=2021-09-01&endDate=2021-09-30&type=month&masterAdminId=1234")
    Call<GetDashBoardResponse> dashBoardApiCall();

   @Headers({"orgurl:brigosha.classroom.digital"})
    @GET("/api/master-care/admin/org-list?name=ri")
    Call<GetOrgListResponse> getOrgApiCall();

    @Headers({"orgurl:brigosha.classroom.digital"})
    @GET("/api/master-care/admin/users-list?orgId=f8608497-fb34-4abd-a8ad-3fb7ed73f51d&masterAdminId=1234")
    Call<GetUserFileResponse> getOrgFileApiCall();

}
