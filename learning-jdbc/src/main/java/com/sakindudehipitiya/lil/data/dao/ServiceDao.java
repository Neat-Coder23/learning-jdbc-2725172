package com.sakindudehipitiya.lil.data.dao;

import com.sakindudehipitiya.lil.data.entity.Service;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import com.sakindudehipitiya.lil.data.util.DatabaseUtils;


public class ServiceDao implements Dao<Service, UUID>{
  private static final Logger LOGGER = Logger.getLogger(ServiceDao.class.getName());

  private static final String GET_ALL = "select service_id, name, price from wisdom.services";

  @Override
  public List<Service> getAll(){
    List<Service> services = new ArrayList<>();
    Connection connection = DatabaseUtils.getConnection();
    try (Statement statement = connection.createStatement()){
      ResultSet rs = statement.executeQuery(GET_ALL);
      services = this.processResultSet(rs);
    }catch(SQLException e){
      DatabaseUtils.handleSqlException("ServiceDao.getAll", e, LOGGER);
    }
    return services;
  }

  @Override
  public Service create(Service entity){
    return null;
  }

  @Override
  public Optional<Service> getOne(UUID id){
    return Optional.empty();
  }

  @Override
  public Service update(Service entity){
    return null;
  }

  @Override
  public void delete(UUID id){

  }

  private List<Service> processResultSet(ResultSet rs) throws SQLException {
    // Implementation to process ResultSet and return a list of Service entities
    List<Service> services = new ArrayList<>();
    while (rs.next()) {
      Service service = new Service();
      // Assuming Service has fields that match the columns in the ResultSet
      service.setServiceId((UUID) rs.getObject("service_id"));
      service.setName(rs.getString("name"));
      service.setPrice(rs.getBigDecimal("price"));
      // Add other fields as necessary
      services.add(service);
    }
    return services;
  }
}
