package com.enjoy.Spring.service.weapon.impl;

import com.enjoy.Spring.service.weapon.WeaponService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary // case3. 우선순위 방식
@Service
public class GunWeaponServiceImpl implements WeaponService {

    @Override
    public void attack() {
        System.out.println(">>> Gun Attack !!!");
    }
}
