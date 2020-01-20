package ch.heigvd.amt.chillout.api.endpoints;

import ch.heigvd.amt.chillout.api.ClientsApi;
import ch.heigvd.amt.chillout.api.exceptions.ApiException;
import ch.heigvd.amt.chillout.api.model.Client;
import ch.heigvd.amt.chillout.api.model.InlineObject1;
import ch.heigvd.amt.chillout.api.service.ClientService;
import ch.heigvd.amt.chillout.entities.ClientEntity;
import ch.heigvd.amt.chillout.repositories.ClientRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

@RestController
public class ClientsApiController implements ClientsApi{
    @Autowired
    ClientRepository clientRepository;
    
    @Autowired
    ClientService clientService;

    /**
     * Creates a Client
     * @param client
     * @return
     * @throws ApiException
     */
    public ResponseEntity<Object> createClient(@ApiParam(value = "", required = true) @Valid @RequestBody Client client) throws ApiException {
        ClientEntity clientEntity = clientService.createClient(client);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{email}")
                .buildAndExpand(clientEntity.getEmail()).toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * Gets a Client by its ID
     * @param email
     * @return
     * @throws ApiException
     */
    public ResponseEntity<Client> getClientById(@ApiParam(value = "The email of the clienz", required = true) @PathVariable("email") String email) throws ApiException {

        ClientEntity clientEntity = clientService.getClientByEmail(email);
        return ResponseEntity.ok(clientService.toClient(clientEntity));
    }

    /**
     * Gets all the Clients
     * @param page
     * @param pageSize
     * @return
     * @throws ApiException
     */
    public ResponseEntity<List<Client>> getClients(@Min(1)@ApiParam(value = "Number of the page", defaultValue = "1") @Valid @RequestParam(value = "page", required = false, defaultValue="1") Integer page,@Min(1)@ApiParam(value = "Size of the page", defaultValue = "20") @Valid @RequestParam(value = "pageSize", required = false, defaultValue="20") Integer pageSize) throws ApiException {

        List<Client> users = clientService.getClients(page,pageSize);
        return ResponseEntity.ok(users);
    }

    /**
     * Deletes a Client by its ID
     * @param email
     * @return
     * @throws ApiException
     */
    public ResponseEntity<Object> deleteClient(@ApiParam(value = "The email of the client", required = true) @PathVariable("email") String email) throws ApiException {

        clientService.deleteClientByEmail(email);
        return ResponseEntity.ok().build();
    }

}
