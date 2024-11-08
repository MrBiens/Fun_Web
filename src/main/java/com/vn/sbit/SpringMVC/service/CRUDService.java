package com.vn.sbit.SpringMVC.service;

import java.util.List;

public interface CRUDService<T,R,U,V>{
    public List<V> getAll();

    public V create(R s);

    public V updateById(Long id,U u);


    public void deleteById(Long id);

}
