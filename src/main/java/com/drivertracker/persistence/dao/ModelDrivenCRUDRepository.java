package com.drivertracker.persistence.dao;

import java.io.Serializable;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Use this ModelDrivenCRUDRepository on user specific repository interface. Example: see test-case sample
 * com.ncaller.elasticsearch.repository.PersonRepository.
 * 
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface ModelDrivenCRUDRepository<T, ID extends Serializable> extends ElasticsearchRepository<T, ID> {

}
