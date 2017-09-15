package br.com.aramosdev.zapimoveis.model.api;

import br.com.aramosdev.zapimoveis.model.contact.ContactDataRequest;
import br.com.aramosdev.zapimoveis.model.contact.ContactResponse;
import br.com.aramosdev.zapimoveis.model.properties.PropertiesResponse;
import br.com.aramosdev.zapimoveis.model.properties.PropertyDetailResponse;
import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Alberto.Ramos on 12/09/17.
 */

public interface Services {

    @GET("/imoveis")
    Flowable<PropertiesResponse> getProperties();

    @GET("/imoveis/{id}")
    Flowable<PropertyDetailResponse> getPropertyDetail(@Path("id") long id);

    @POST("/imoveis/contato")
    Flowable<ContactResponse> getPropertyDetail(@Body ContactDataRequest request);
}
