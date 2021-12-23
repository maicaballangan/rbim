package models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import play.db.jpa.GenericModel;

@MappedSuperclass
public class Model extends GenericModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
    public Long id;

    public Long getId() {
        return id;
    }

    @Override
    public Object _key() {
        return getId();
    }

}