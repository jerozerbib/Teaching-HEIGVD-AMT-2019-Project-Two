package ch.heig.vd.amt.chillout.api.endpoints;

import ch.heig.vd.amt.chillout.repositories.ClientRepository;
import io.avalia.fruits.api.ClientsApi;
import ch.heig.vd.amt.chillout.entities.ClientEntity;
import io.avalia.fruits.api.model.Client;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class ClientsApiController implements ClientsApi {

    @Autowired
    ClientRepository fruitRepository;

    public ResponseEntity<Object> createFruit(@ApiParam(value = "", required = true) @Valid @RequestBody Client client) {
        ClientEntity newClientEntity = toFruitEntity(client);
        fruitRepository.save(newClientEntity);
        Integer id = newClientEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newClientEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    public ResponseEntity<List<Client>> getFruits() {
        List<Client> fruits = new ArrayList<>();
        for (ClientEntity clientEntity : fruitRepository.findAll()) {
            fruits.add(toClient(clientEntity));
        }
        return ResponseEntity.ok(fruits);
    }


    private ClientEntity toFruitEntity(Client client) {
        ClientEntity entity = new ClientEntity();
        entity.setId(client.getId());
        entity.setName(client.getName());
        entity.setUsername(client.getUsername());
        entity.setAdmin(client.getIsAdmin());
        return entity;
    }

    private Client toClient(ClientEntity entity) {
        Client client = new Client();
        client.setId(entity.getId());
        client.setName(entity.getName());
        client.setUsername(entity.getUsername());
        client.setIsAdmin(entity.isAdmin());
        return client;
    }

}
