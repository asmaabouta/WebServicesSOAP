package com.asmaa.msbanque.entities;

import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name ="web",types =Compte.class)
public interface CompteProjection2 {
    public double getSolde();
    public Date getDateCreation();
}
