/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import modul.Country;

import java.util.List;

/**
 *
 * @author nur13
 */
public interface CountryDao {
    List<Country> getAll();

    public Country getById(int id);

    boolean updateCountry(Country u);
    boolean insertCountry(Country u);

    boolean removeCountry(int id);

}
