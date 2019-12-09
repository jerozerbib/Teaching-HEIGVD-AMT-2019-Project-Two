package ch.heigvd.amt.chillout.api.endpoints;

import ch.heigvd.amt.chillout.api.OrderApi;
import ch.heigvd.amt.chillout.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class OrderApiController implements OrderApi {

    @Autowired
    OrderRepository orderRepository;




}
