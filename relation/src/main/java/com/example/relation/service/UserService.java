package com.example.relation.service;

import com.example.relation.dto.AddressRequest;
import com.example.relation.dto.AddressResponse;
import com.example.relation.dto.UserRequest;
import com.example.relation.dto.UserResponse;
import com.example.relation.exception.AddressNotFoundException;
import com.example.relation.exception.UserNotFoundException;
import com.example.relation.model.Address;
import com.example.relation.model.User;
import com.example.relation.repository.AddressRepository;
import com.example.relation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
//    private final Mapper mapper = Mappers.getMapper(Mapper.class);

    private final UserRepository repository;

    private final AddressRepository addressRepository;

    private static User requestToEntity(UserRequest request) {
        User user = User.builder()
                .name(request.getName())
                .build();
        user.setAllAddress(request.getAddresses());
        return user;
    }

    private static UserResponse entityToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .address(user.getAddress())
                .build();
    }


    public UserResponse createUser(UserRequest request) {
        return entityToResponse(repository.save(requestToEntity(request)));
    }

    public void deleteUser(Long id) {
        repository.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException("User not found with id : " + id);
        });
        repository.deleteById(id);
    }

    public UserResponse findById(Long id) {
        return entityToResponse(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new UserNotFoundException("User not found with id : " + id);
                        }));

    }

    public UserResponse updateUserByName(Long id, String name) {
        User user = repository.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException("User not found with id : " + id);
        });
        user.setName(name);
        repository.save(user);
        return entityToResponse(user);
    }


    public List<AddressResponse> createAddresses(Long id, List<AddressRequest> requests) {
        User user = repository.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException("User not found with id : " + id);
        });
        List<Address> addresses = requests.stream().map(address -> new Address(address.getStreet())).collect(Collectors.toList());
        user.setAllAddress(addresses);
        repository.save(user);
        return addresses.stream().map(response -> new AddressResponse(response.getId(), response.getStreet(), response.getUser())).toList();
    }


    public AddressResponse findAddressById(Long id) {
        return entityToAddressResponse(addressRepository.findById(id).orElseThrow(() ->
        {
            throw new AddressNotFoundException("Address not found id : " + id);
        }));
    }

    public AddressResponse entityToAddressResponse(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .street(address.getStreet())
                .user(address.getUser())
                .build();
    }

    public UserResponse updateUser(Long id, UserRequest request, String street) {
        User user = repository.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException("User not found with id : " + id);
        });
        user.setName(request.getName());
        Address address = addressRepository.findAddressByStreet(street).orElseThrow(() -> {
            throw new AddressNotFoundException("Address not found ");
        });
        address.setStreet(request.getAddresses().stream().findAny().get().getStreet());
        repository.save(user);
        addressRepository.save(address);
        return entityToResponse(user);
    }

    public void deleteAddress(Long id) {
        addressRepository.findById(id).orElseThrow(() -> {
            throw new AddressNotFoundException("Address not found id : " + id);
        });
        addressRepository.deleteById(id);
    }

    public AddressResponse updateAddress(Long id, AddressRequest addressRequest) {
        Address address = addressRepository.findById(id).orElseThrow(() -> {
            throw new AddressNotFoundException("Address not found id : " + id);
        });
        address.setStreet(addressRequest.getStreet());
        addressRepository.save(address);

        return AddressResponse.builder()
                .id(address.getId())
                .street(address.getStreet())
                .build();

    }

}
