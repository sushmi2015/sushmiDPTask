package dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.ProductEntity;

public interface ProductDao extends JpaRepository<ProductEntity, Integer> {

	public Optional<ProductEntity> findByDescription(String productDesc);

	public void deleteByDescription(String productDesc);

}
