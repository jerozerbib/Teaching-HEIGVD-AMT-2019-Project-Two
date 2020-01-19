//package ch.heigvd.amt.chillout.api.service;
//
//import ch.heigvd.amt.chillout.api.exceptions.ApiException;
//import ch.heigvd.amt.chillout.entities.OrderEntity;
//import ch.heigvd.amt.chillout.repositories.OrderRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//
//public class OrdersService {
//
//    @Autowired
//    OrderRepository orderRepository;
//
////    public UserEntity createUser(UserInput user) throws ApiException {
////
////        if (userRepository.existsById(user.getEmail())) {
////            throw new ApiException(HttpStatus.CONFLICT,"User already exists.");
////        }
////        UserEntity userEntity = toUserEntity(user);
////        userRepository.save(userEntity);
////        return userEntity;
////    }
//
//    public OrderEntity getUserByEmail(Long id) throws ApiException {
//        OrderEntity userEntity = orderRepository.findById(id).orElse(null);
//        if (userEntity == null){
//            throw new ApiException(HttpStatus.NOT_FOUND,"User not found");
//        }
//        return userEntity;
//    }
//
//    public List<UserOutput> getAllUsers(int numPage, int pageSize) {
//
//        Pageable paging = PageRequest.of(numPage,pageSize);
//        Page<UserOutput> pagedResult = userRepository.findAll(paging);
//
//        if(pagedResult.hasContent()){
//            return pagedResult.getContent();
//        }else {
//            return new ArrayList<>();
//        }
//    }
//
//    public void updateUser(String email, @Valid InlineObject fields) throws ApiException {
//
//        UserEntity userEntity = getUserByEmail(email);
//
//        String isBlocked = fields.getBlocked();
//        String password = fields.getPassword();
//
//        if (isBlocked != null){
//            if(userEntity.getIsAdmin() == 1) {
//                userEntity.setIsBlocked(Integer.parseInt(isBlocked));
//            }else {
//                throw new ApiException(HttpStatus.FORBIDDEN, "Only admin can block/unblock users");
//            }
//        }
//        if (password != null){
//            userEntity.setPassword(authService.hashAndEncode(password));
//        }
//        userRepository.save(userEntity);
//    }
//
//    public void deleteUserByEmail(String email) throws ApiException {
//        UserEntity userEntity = getUserByEmail(email);
//        userRepository.delete(userEntity);
//    }
//
//    /**
//     * Converts a UserInput into a UserEntity
//     *
//     * @param user to convert
//     * @return a UserEntity
//     */
//    public UserEntity toUserEntity(UserInput user) {
//        UserEntity entity = new UserEntity();
//        entity.setEmail(user.getEmail());
//        entity.setFirstname(user.getFirstname());
//        entity.setLastname(user.getLastname());
//        entity.setPassword(authService.hashAndEncode(user.getPassword()));
//        entity.setIsAdmin(user.getIsAdmin());
//        entity.setIsBlocked(user.getIsBlocked());
//        return entity;
//    }
//
//    /**
//     * Converts a UserEntity in a UserOutput
//     *
//     * @param entity to convert
//     * @return a UserOutput
//     */
//    public UserOutput toUser(UserEntity entity) {
//        UserOutput user = new UserOutput();
//        user.setEmail(entity.getEmail());
//        user.setFirstname(entity.getFirstname());
//        user.setLastname(entity.getLastname());
//        user.setIsAdmin(entity.getIsAdmin());
//        user.setIsBlocked(entity.getIsBlocked());
//        return user;
//    }
//
//}
