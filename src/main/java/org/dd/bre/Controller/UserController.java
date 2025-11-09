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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('CUSTOMER')")
public class UserController {
    private final UserService userService;
    private final OrderService orderService;
    private final AddressService addressService;
    private final SettingService settingService;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDto> getUserProfileHandler(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userService.getUserProfile(userDetails));
    }

    @PutMapping("/profile")
    public ResponseEntity<UserProfileDto> updateProfile(@Valid @RequestBody UserProfileDto user,
                                                        @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok().body(userService.updateProfile(userDetails,user));
    }

    @GetMapping("/orders")
    public ResponseEntity<OrderPageResponse> getOrdersHistoryHandler(@RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "5") int size,
                                                                     @AuthenticationPrincipal UserDetails userDetails) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(orderService.getOrdersHistory(userDetails,pageable));
    }

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressResponse>> getAddressesOfUserHandler(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(addressService.getAddresses(userDetails));
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<Void> deleteAddressHandler(@PathVariable Long id,
                                                     @AuthenticationPrincipal UserDetails userDetails){
        addressService.deleteAddress(userDetails,id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/address")
    public ResponseEntity<AddressResponse> addAddressHandler(@Valid @RequestBody AddressRequest addressRequest,
                                                             @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(addressService.addAddress(userDetails,addressRequest));
    }

    @PutMapping("/address/{id}")
    public ResponseEntity<AddressResponse> updateAddressHandler(@PathVariable Long id , @Valid @RequestBody AddressRequest addressRequest,
                                                                @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(addressService.updateAddress(userDetails,id,addressRequest));
    }

    @GetMapping("/settings")
    public ResponseEntity<SettingResponse> getSettingHandler(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(settingService.getSetting(userDetails));
    }
    @PutMapping("/settings")
    public ResponseEntity<SettingResponse> updateSettingHandler(@RequestParam boolean isEmailEnabled,@RequestParam boolean isPhoneEnabled,
                                                                @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(settingService.updateSetting(userDetails,isEmailEnabled,isPhoneEnabled));
    }

}
