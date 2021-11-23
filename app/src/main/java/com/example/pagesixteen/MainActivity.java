package com.example.pagesixteen;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.appsearch.GetByDocumentIdRequest;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pagesixteen.ApiPackage.ApiClient;
import com.example.pagesixteen.ApiPackage.GetDashBoardRequest;
import com.example.pagesixteen.ApiPackage.GetDashBoardResponse;
import com.example.pagesixteen.ApiPackage.GetOrgListResponse;
import com.example.pagesixteen.ApiPackage.GetUserFileResponse;
import com.example.pagesixteen.ApiPackage.LoginService;
import com.example.pagesixteen.ApiPackage.SignInRequest;
import com.example.pagesixteen.ApiPackage.SignInResponse;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    SignInRequest signInRequest;
    SignInResponse signInResponse;
    LoginService loginService;
    Retrofit retrofit;
    GetDashBoardResponse getDashBoardResponse;
    GetOrgListResponse getOrgListResponse;
    GetUserFileResponse getUserFileResponse;


    ArrayList<VaccineCard> card = new ArrayList<>();
    RecyclerView recyclerView;

    RecyclerView.Adapter cardAdapter;
    RecyclerView.LayoutManager cardLayoutManager;

    ArrayList<VaccineDrive> vaccineDrive = new ArrayList<>();
    RecyclerView recyclerView_drive;

    RecyclerView.Adapter cardAdapterDrive;
    RecyclerView.LayoutManager cardLayoutManagerDrive;

    ArrayList<QuestionCards> questionCards = new ArrayList<>();
    RecyclerView recyclerViewQuestion;

    RecyclerView.Adapter cardAdapterQuestion;
    RecyclerView.LayoutManager cardLayoutManagerQuestion;

    ArrayList<OrganizationCard> organizationCards = new ArrayList<>();
    RecyclerView recyclerViewOrganization;

    RecyclerView.Adapter cardAdapterOrganization;
    RecyclerView.LayoutManager cardLayoutManagerOrganization;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInit();
        login();
       getDashboard();
       getOrgList();
        getUserList();


        createCard();
        buildRecyclerView();

        //card2 _for vaccination drive
        createCardDrive();
        buidRecylerViewDrive();

        //cards 3 for question cards

        registerVaccine();
        buildRecyclerViewRegister();

        //cards 4 for health organization

        createCardOrganization();
        buildRecyclerViewOrganization();


    }


    //card1

    public void createCard() {
        card = new ArrayList<>();
        card.add(new VaccineCard("Covishield", "Serum Institute of India", R.drawable.covidshield_1));
        card.add(new VaccineCard("Covaxin", "Bharat Biotech", R.drawable.covidshield_1));
        card.add(new VaccineCard("Sputnik V", "Gamaleya", R.drawable.sputnik));
        card.add(new VaccineCard("mRNA-1273", "Moderna", R.drawable.moderna));
        card.add(new VaccineCard("ZyCoV-D", "Zydus Cadila", R.drawable.zycob));
        card.add(new VaccineCard("Ad26.COV2.S", "Jhonson & Jonson's Janssen", R.drawable.janssen));
        card.add(new VaccineCard("AZD1222", "Oxford/AstraZeneca", R.drawable.astrazeneca));

    }

    public void buildRecyclerView() {
        recyclerView = findViewById(R.id.cardRecyclerView);
        recyclerView.setHasFixedSize(true);

        cardLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(cardLayoutManager);

        cardAdapter = new CardAdapter(card);

        recyclerView.setAdapter(cardAdapter);


    }


    //card 2
    public void createCardDrive() {
        vaccineDrive.add(new VaccineDrive("CoWin Registrations", "53.47Crores", " +61.43Laks yesterday"));
        vaccineDrive.add(new VaccineDrive("Vaccinations Delivered", "60.29Crores", "+80.1Laksh yesterday"));
        vaccineDrive.add(new VaccineDrive("Fully Vaccinated", "13.57Crores", "   +21.91Lakhs yesterday"));

    }

    public void buidRecylerViewDrive() {
        recyclerView_drive = findViewById(R.id.recycleView_registration_drive);
        recyclerView_drive.setHasFixedSize(true);
        cardLayoutManagerDrive = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_drive.setLayoutManager(cardLayoutManagerDrive);

        cardAdapterDrive = new CardAdapterDrive(vaccineDrive);

        recyclerView_drive.setAdapter(cardAdapterDrive);
    }

    //card3

    public void registerVaccine() {

        questionCards.add(new QuestionCards(R.drawable.vaccine, "Vaccine Registration"));
        questionCards.add(new QuestionCards(R.drawable.about, "About the Vaccine"));
        questionCards.add(new QuestionCards(R.drawable.who, " Who will get the Vaccine?"));
        questionCards.add(new QuestionCards(R.drawable.how, "How will we be vaccinated?"));
        questionCards.add(new QuestionCards(R.drawable.what, "What is expect before vaccination?"));
        questionCards.add(new QuestionCards(R.drawable.whatafter, "What to expect after Vaccination"));

    }

    public void buildRecyclerViewRegister() {


        recyclerViewQuestion = findViewById(R.id.cardRecyclerView_for_quesrtion);
        recyclerViewQuestion.setHasFixedSize(true);
        cardLayoutManagerQuestion = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewQuestion.setLayoutManager(cardLayoutManagerQuestion);

        cardAdapterQuestion = new QuestionCardAdapter(questionCards);

        recyclerViewQuestion.setAdapter(cardAdapterQuestion);
    }

    //card4

    public void createCardOrganization() {

        organizationCards = new ArrayList<>();
        organizationCards.add(new OrganizationCard("WHO India", R.drawable.who_india));
        organizationCards.add(new OrganizationCard("National Health portal of India", R.drawable.national_health));
        organizationCards.add(new OrganizationCard("Public Health Foundation of India", R.drawable.public_health));
        organizationCards.add(new OrganizationCard("Ministry of Health and Family Welfare", R.drawable.ministry_of_health_and_family_welfare));
        organizationCards.add(new OrganizationCard("Indian Council of Medical Research", R.drawable.indian_council_of_medical_research));
        organizationCards.add(new OrganizationCard("MSF India", R.drawable.msf));
        organizationCards.add(new OrganizationCard("UNICEF India", R.drawable.unicef));

    }

    public void buildRecyclerViewOrganization() {


        recyclerViewOrganization = findViewById(R.id.organization_recyclerView);
        recyclerViewOrganization.setHasFixedSize(true);
        cardLayoutManagerOrganization = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewOrganization.setLayoutManager(cardLayoutManagerOrganization);

        cardAdapterOrganization = new OrganizationAdapterCard(organizationCards);

        recyclerViewOrganization.setAdapter(cardAdapterOrganization);

    }

    private void login() {

        signInRequest = new SignInRequest("fahd.hazarika@brigosha.com", "1234", true);
        Call<SignInResponse> call = loginService.signInApiCall(signInRequest);


        call.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_SHORT).show();
                }
                signInResponse = response.body();
                Toast.makeText(getApplicationContext(), signInResponse.user.name, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void apiInit() {
        retrofit = ApiClient.getRetrofit();
        loginService = ApiClient.getLoginService();
    }

    public void getDashboard() {
        Call<GetDashBoardResponse> call = loginService.dashBoardApiCall();
        call.enqueue(new Callback<GetDashBoardResponse>() {
            @Override
            public void onResponse(Call<GetDashBoardResponse> call, Response<GetDashBoardResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_SHORT).show();
                }
                getDashBoardResponse = response.body();
                Toast.makeText(getApplicationContext(), getDashBoardResponse.allInstitutes.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<GetDashBoardResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            }
        });
    }

   public void getOrgList() {
        Call<GetOrgListResponse> call = loginService.getOrgApiCall();
        call.enqueue(new Callback<GetOrgListResponse>() {
            @Override
            public void onResponse(Call<GetOrgListResponse> call, Response<GetOrgListResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_SHORT).show();
                }
                getOrgListResponse = response.body();
                Toast.makeText(getApplicationContext(), getOrgListResponse.conversationCount.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<GetOrgListResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getUserList() {
        Call<GetUserFileResponse> call = loginService.getOrgFileApiCall();
        call.enqueue(new Callback<GetUserFileResponse>() {
            @Override
            public void onResponse(Call<GetUserFileResponse> call, Response<GetUserFileResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_SHORT).show();
                }
                getUserFileResponse = response.body();
                Toast.makeText(getApplicationContext(), getUserFileResponse.users.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<GetUserFileResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

