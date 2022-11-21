package com.formation.velo.api.client.defibrillator;

import retrofit2.Call;
import retrofit2.http.GET;
public interface OpenDataNantesClient {
    
    @GET("https://data.nantesmetropole.fr/api/records/1.0/search/?dataset=244400404_defibrillateurs-nantes&q=&rows=155&sort=designation&facet=nature_site&facet=libelle_quartier&facet=commune")
    Call<OpenData> getRecords();
}
