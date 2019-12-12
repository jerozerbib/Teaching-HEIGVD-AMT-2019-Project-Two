package ch.heigvd.amt.chillout.api.endpoints;

import ch.heigvd.amt.chillout.api.ClientsApi;
import ch.heigvd.amt.chillout.api.model.ClientInput;
import ch.heigvd.amt.chillout.api.model.ClientOutput;
import ch.heigvd.amt.chillout.repositories.ClientRepository;
import ch.heigvd.amt.chillout.entities.ClientEntity;
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
    ClientRepository clientRepository;

    /**
     * Creates a new Client from the ClientInput of the Swagger file with a POST method request
     * @param client ClientInput that gives all the needed information necessary to create a Client
     * @return a new Object
     */
    public ResponseEntity<Object> createClient(@ApiParam(value = "", required = true) @Valid @RequestBody ClientInput client) {
        ClientEntity newClientEntity = toClientEntity(client);
        clientRepository.save(newClientEntity);
        Long id = newClientEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newClientEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * Gets all the Client in the database with the GET method request
     * @return all the clients
     */
    public ResponseEntity<List<ClientOutput>> getClients() {
        List<ClientOutput> clients = new ArrayList<>();
        for (ClientEntity clientEntity : clientRepository.findAll()) {
            clients.add(toClient(clientEntity));
        }
        return ResponseEntity.ok(clients);
    }

    /**
     * Converts a ClientInput to a ClientEntity
     * @param client Input
     * @return a ClientEntity
     */
    private ClientEntity toClientEntity(ClientInput client) {
        ClientEntity entity = new ClientEntity();
        entity.setName(client.getName());
        entity.setUsername(client.getUsername());
        entity.setPassword(client.getPassword());
        entity.setSalt(client.getSalt());
        entity.setAdmin(client.getIsAdmin());
        return entity;
    }

    /**
     * Converts a ClientEntity to a ClientOutput
     * @param entity to convert
     * @return a ClientOutput
     */
    private ClientOutput toClient(ClientEntity entity) {
        ClientOutput client = new ClientOutput();
        client.setId(client.getId());
        client.setName(entity.getName());
        client.setUsername(entity.getUsername());
        return client;
    }

}
