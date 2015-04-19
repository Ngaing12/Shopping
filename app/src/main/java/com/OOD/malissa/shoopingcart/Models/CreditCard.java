package com.OOD.malissa.shoopingcart.Models;

import java.io.Serializable;

/**
 * Created by Malissa on 3/29/2015.
 */
public class CreditCard implements Serializable {

    private String _accNumber;
    private String _expiration;

    public CreditCard(String accountNum, String expiry){

        this._accNumber = accountNum;
        this._expiration = expiry;
    }


    //region Accessors
    public String getAccNumber() {return _accNumber;}
    public String getExpiration() { return _expiration;}
    //endregion

}
