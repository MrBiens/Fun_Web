package com.vn.sbit.SpringMVC.service;

import java.util.List;

public interface CRUDService<T,R,V>{
    public List<V> getAll();

    public V create(R s);

    public V updateById(Long id);


    public void deleteById(Long id);

}
