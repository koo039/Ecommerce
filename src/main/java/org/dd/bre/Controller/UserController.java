package org.dd.bre.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dd.bre.Dto.*;
import org.dd.bre.Service.AddressService;
import org.dd.bre.Service.OrderService;
import org.dd.bre.Service.SettingService;
import org.dd.bre.Service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final OrderService orderService;
    private final AddressService addressService;
    private final SettingService settingService;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDto> getUserProfileHandler() {
        Long userId = getAuthenticatedUserId();
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }

    @PutMapping("/profile")
    public ResponseEntity<UserProfileDto> updateProfile(@Valid @RequestBody UserProfileDto user){
        Long userId = getAuthenticatedUserId();
        return ResponseEntity.ok().body(userService.updateProfile(userId,user));
    }

    @GetMapping("/orders")
    public ResponseEntity<OrderPageResponse> getOrdersHistoryHandler(@RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "5") int size) {
        Long userId = getAuthenticatedUserId();
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(orderService.getOrdersHistory(userId,pageable));
    }

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressResponse>> getAddressesOfUserHandler(){
        Long userId = getAuthenticatedUserId();
        return ResponseEntity.ok(addressService.getAddresses(userId));
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<Void> deleteAddressHandler(@PathVariable Long id){
        Long userId = getAuthenticatedUserId();
        addressService.deleteAddress(userId,id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/address")
    public ResponseEntity<AddressResponse> addAddressHandler(@Valid @RequestBody AddressRequest addressRequest){
        Long userId = getAuthenticatedUserId();
        return ResponseEntity.ok(addressService.addAddress(userId,addressRequest));
    }

    @PutMapping("/address/{id}")
    public ResponseEntity<AddressResponse> updateAddressHandler(@PathVariable Long id , @Valid @RequestBody AddressRequest addressRequest){
        Long userId = getAuthenticatedUserId();
        return ResponseEntity.ok(addressService.updateAddress(userId,id,addressRequest));
    }

    @GetMapping("/settings")
    public ResponseEntity<SettingResponse> getSettingHandler(){
        Long userId = getAuthenticatedUserId();
        return ResponseEntity.ok(settingService.getSetting(userId));
    }
    @PutMapping("/settings")
    public ResponseEntity<SettingResponse> updateSettingHandler(@RequestParam boolean isEmailEnabled,@RequestParam boolean isPhoneEnabled){
        Long userId = getAuthenticatedUserId();
        return ResponseEntity.ok(settingService.updateSetting(userId,isEmailEnabled,isPhoneEnabled));
    }

    private Long getAuthenticatedUserId() {
        return 1L;
    }
}
