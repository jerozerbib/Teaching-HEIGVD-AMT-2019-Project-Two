package ch.heigvd.amt.chillout.api.service;

import ch.heigvd.amt.chillout.api.exceptions.ApiException;
import ch.heigvd.amt.chillout.api.model.Client;
import ch.heigvd.amt.chillout.api.model.InlineObject1;
import ch.heigvd.amt.chillout.entities.ClientEntity;
import ch.heigvd.amt.chillout.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;


    /**
     * Creates a Client
     * @param client
     * @return
     * @throws ApiException
     */
    public ClientEntity createClient(@Valid Client client) throws ApiException {

        if (clientRepository.existsById(client.getEmail())) {
            throw new ApiException(HttpStatus.CONFLICT,"Client already exists.");
        }
        ClientEntity clientEntity = toClientEntity(client);
        clientRepository.save(clientEntity);
        return clientEntity;

    }

    /**
     * Deletes a Client by its Email
     * @param email
     * @throws ApiException
     */
    public void deleteClientByEmail(String email) throws ApiException {
        ClientEntity clientEntity = getClientByEmail(email);
        clientRepository.delete(clientEntity);
    }

    /**
     * Gets a Client by its email
     * @param email
     * @return
     * @throws ApiException
     */
    public ClientEntity getClientByEmail(String email) throws ApiException {

        ClientEntity clientEntity = clientRepository.findById(email).orElse(null);
        if (clientEntity == null){
            throw new ApiException(HttpStatus.NOT_FOUND,"Client not found");
        }
        return clientEntity;
    }

    /**
     * Get all the clients
     * @param numPage
     * @param pageSize
     * @return
     */
    public List<Client> getClients(@Min(1) @Valid Integer numPage, @Min(1) @Valid Integer pageSize) {

        Pageable paging = PageRequest.of(numPage,pageSize);
        Page<Client> pagedResult = clientRepository.findAll(paging);

        if(pagedResult.hasContent()){
            return pagedResult.getContent();
        }else {
            return new ArrayList<>();
        }
    }

    /**
     * Converts a Client to a ClientEntity
     * @param client
     * @return
     */
    public ClientEntity toClientEntity(@Valid Client client) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setEmail(client.getEmail());
        return clientEntity;
    }

    /**
     * Converts a ClientEntity to a ClientOutpur
     * @param entity to convert
     * @return a ClientOutput
     */
    public Client toClient(ClientEntity entity) {
        Client client = new Client();
        client.setEmail(entity.getEmail());
        return client;
    }
}
