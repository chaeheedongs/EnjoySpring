package com.enjoy.Spring.controller;

import com.enjoy.Spring.service.weapon.WeaponService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/weapon/v1")
public class
WeaponController {

    /**
     * Spring은 DI 진행시 타입을 기반으로 선 조회함
     * --> 그러나 중복되는 빈이 발생하게 된다면?
     * 1. "필드명"으로 찾음
     * 2. @Qualifier 어노테이션 내 "구분자"로 찾음
     * 3. @Primary "우선순위"로 찾음
     * ---------------------------------------------
     * @Qualifier 와 @Primary가 중복일 경우 @Qualifier를 우선순위로 둔다.
     */

    /* case1. 필드명 방식 */
//    private final WeaponService knifeWeaponServiceImpl;
//
//    public WeaponController(final WeaponService knifeWeaponServiceImpl) {
//        this.knifeWeaponServiceImpl = knifeWeaponServiceImpl;
//    }

    /* case2. @Qualifier("구현체이름") 구분자 주입 방식 (추천) */
    private final WeaponService weaponService;

    public WeaponController(@Qualifier("knifeWeaponServiceImpl") final WeaponService weaponService) {
        this.weaponService = weaponService;
    }

    /**
     * http -v :8080/rest/weapon/v1/attack
     */
    @GetMapping("/attack")
    public ResponseEntity attack() {
        weaponService.attack();
        return ResponseEntity.status(HttpStatus.OK)
                             .body("weapon attack");
    }
}
