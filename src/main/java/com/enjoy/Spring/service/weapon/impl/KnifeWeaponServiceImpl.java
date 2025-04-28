package com.enjoy.Spring.service.weapon.impl;

import com.enjoy.Spring.service.weapon.WeaponService;
import org.springframework.stereotype.Service;

@Service
public class KnifeWeaponServiceImpl implements WeaponService {

    @Override
    public void attack() {
        System.out.println(">>> Knife Attack !!!");
    }
}
